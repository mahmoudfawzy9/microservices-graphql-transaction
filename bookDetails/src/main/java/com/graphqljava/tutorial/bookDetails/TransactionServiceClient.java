package com.graphqljava.tutorial.bookDetails;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "transaction-service", url = "${transaction.service.url}")
public interface TransactionServiceClient {

    @PostMapping
    Transaction createTransaction(@RequestBody Transaction transaction);

    @GetMapping("/{accountNumber}")
    List<Transaction> getTransactions(@PathVariable String accountNumber);
}