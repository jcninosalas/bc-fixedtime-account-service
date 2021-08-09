package com.everis.fixedaccountservice.controller;

import com.everis.fixedaccountservice.model.FixedTimeAccount;
import com.everis.fixedaccountservice.service.FixedTimeAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class FixedTimeAccountController {

    @Autowired
    private FixedTimeAccountService service;

    @PostMapping("/f-account/new")
    public Mono<?> createAccount() {
        return service.create();
    }

    @GetMapping("/f-account/find")
    public Mono<FixedTimeAccount> getAccountById(@RequestParam String account) {
        return service.getByAccountNumber(account);
    }

    @GetMapping("/f-account")
    public Flux<FixedTimeAccount> getAllAccounts(){
        return service.findAll();
    }

    @PutMapping("/f-account/disable")
    public Mono<FixedTimeAccount> disableAccount(@RequestParam String account) {
        return service.disable(account);
    }

    @PutMapping("/f-account/update")
    public Mono<FixedTimeAccount> updateAccount(@RequestBody FixedTimeAccount account) {
        return service.update(account);
    }

}
