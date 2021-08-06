package com.everis.fixedaccountservice.service;

import com.everis.fixedaccountservice.model.FixedTimeAccount;
import com.everis.fixedaccountservice.repository.FixedTimeAccountRepository;
import com.everis.fixedaccountservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Date;

public class FixedTimeAccountService {

    @Autowired
    private FixedTimeAccountRepository repository;

    public Flux<FixedTimeAccount> findAll() {
        return repository.findAll();
    }

    public Mono<FixedTimeAccount> getByAccountNumber(String accountNumber) {
        return repository.findByAccountNumber(accountNumber);
    }

    public Mono<FixedTimeAccount> create() {
        Mono<FixedTimeAccount> account = setNewFixedTimeAccount();
        return account.flatMap(repository::insert);
    }

    public Mono<FixedTimeAccount> update( FixedTimeAccount account) {
        return repository.findByAccountNumber(account.getAccountNumber())
                //.switchIfEmpty(Mono.error(new Exception("Cuenta no registrada")))
                .map(a -> {account.setId(a.getId()); return a;})
                .flatMap(repository::save);
    }

    public Mono<FixedTimeAccount> disable(String accountNumber) {
        return repository.findByAccountNumber(accountNumber)
                //.switchIfEmpty(Mono.error(new Exception("Cuenta no registrada")))
                .map(a -> {a.setIsActive(false); return a;})
                .flatMap(repository::save);
    }

    private Mono<FixedTimeAccount> setNewFixedTimeAccount() {
        FixedTimeAccount account = new FixedTimeAccount();
        account.setAccountNumber(Utils.generateAccountNumber());
        account.setCreatedAt(new Date());
        account.setAccountBalance(new BigDecimal(0));
        account.setIsActive(true);
        return Mono.just(account);
    }
}
