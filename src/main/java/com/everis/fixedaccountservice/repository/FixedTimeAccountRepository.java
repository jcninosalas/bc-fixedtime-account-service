package com.everis.fixedaccountservice.repository;

import com.everis.fixedaccountservice.model.FixedTimeAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface FixedTimeAccountRepository extends ReactiveMongoRepository<FixedTimeAccount, String> {

    Mono<FixedTimeAccount> findByAccountNumber(String account);
}
