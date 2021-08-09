package com.everis.fixedaccountservice.service;

import com.everis.fixedaccountservice.bean.AccountClientBean;
import com.everis.fixedaccountservice.bean.ResponseAccountClient;
import com.everis.fixedaccountservice.repository.FixedTimeAccountRepository;
import com.everis.fixedaccountservice.utils.Webclients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountClientService {

    @Autowired
    Webclients webclients;

    @Autowired
    private FixedTimeAccountRepository repository;

    public Mono<ResponseAccountClient> sendClientAccount(String documentNumber, String numberAccount){
        return repository.findByAccountNumber(numberAccount)
                .flatMap(currentAccount -> {
                    AccountClientBean clientBean =
                            new AccountClientBean(currentAccount.getId(),
                                    currentAccount.getAccountNumber(),
                                    "Fixed", currentAccount.getAccountBalance());

                    return webclients.sendClientAccount(clientBean, documentNumber);
                });
    }
}
