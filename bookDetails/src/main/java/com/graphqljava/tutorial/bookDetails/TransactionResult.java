package com.graphqljava.tutorial.bookDetails;

public class TransactionResult {
    private Transaction transaction;
    private Account fromAccount;
    private Account toAccount;

    public TransactionResult(Transaction savedTransaction, Account updatedFromAccStr, Account updatedToAccStr) {
        this.transaction= savedTransaction;
        this.fromAccount = updatedFromAccStr;
        this.toAccount = updatedToAccStr;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Account getFromAccount() {
        return this.fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return this.toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }
}
