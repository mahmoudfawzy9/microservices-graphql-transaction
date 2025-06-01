package com.graphqljava.tutorial.bookDetails;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "account-service", url = "${account.service.url}")
public interface AccountServiceClient {

    @GetMapping("/{accountNumber}")
    Account getAccount(@PathVariable String accountNumber);

    @PutMapping("/{accountNumber}/balance")
    Account updateBalance(@PathVariable String accountNumber, @RequestParam Double amount);
}