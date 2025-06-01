package com.graphqljava.tutorial.bookDetails;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GraphQlController {

    private final AccountServiceClient accountServiceClient;
    private final TransactionServiceClient transactionServiceClient;

    public GraphQlController(AccountServiceClient accountServiceClient,
                             TransactionServiceClient transactionServiceClient) {
        this.accountServiceClient = accountServiceClient;
        this.transactionServiceClient = transactionServiceClient;
    }

    @QueryMapping
    public Account account(@Argument String accountNumber) {
        return accountServiceClient.getAccount(accountNumber);
    }

    @QueryMapping
    public Iterable<Transaction> transactions(@Argument String accountNumber) {
        return transactionServiceClient.getTransactions(accountNumber);
    }

    @MutationMapping
    public TransactionResult transferFunds(@Argument("input") TransactionInput input) {
        // Get accounts
        Account fromAccount = accountServiceClient.getAccount(input.getFromAccount());
        Account toAccount = accountServiceClient.getAccount(input.getToAccount());

        if (fromAccount == null || toAccount == null) {
            throw new RuntimeException("One or both accounts not found");
        }

        if (fromAccount.getBalance() < input.getAmount()) {
            throw new RuntimeException("Insufficient funds");
        }

        // Update balances
        // Update balances
        Account updatedFromAccount = accountServiceClient.updateBalance(input.getFromAccount(), -input.getAmount());
        // Update balances
        Account updatedToAccount = accountServiceClient.updateBalance(input.getToAccount(), input.getAmount());

        // Create transaction record
        Transaction transaction = new Transaction();
        transaction.setFromAccount(input.getFromAccount());
        transaction.setToAccount(input.getToAccount());
        transaction.setAmount(input.getAmount());
        transaction.setStatus("COMPLETED");

        Transaction savedTransaction = transactionServiceClient.createTransaction(transaction);
        try {
            return new TransactionResult(
                    savedTransaction,
                    updatedFromAccount,
                    updatedToAccount
            );
        } catch (Exception e) {
            throw new RuntimeException("Transaction failed: " + e.getMessage());
        }
    }
}