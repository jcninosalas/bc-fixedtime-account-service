package com.everis.fixedaccountservice.controller;

import com.everis.fixedaccountservice.service.AccountClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrentAccountController {

    @Autowired
    AccountClientService accountClientService;

    @PostMapping("/send/account/client")
    public Mono<ResponseEntity<Map<String, Object>>> sendAssociateAccountClient(@RequestParam String documentNumber,
                                                                                @RequestParam String numberAccount){
        Map<String, Object> response = new HashMap<>();
        return accountClientService.sendClientAccount(documentNumber, numberAccount)
                .flatMap(responseAccountClient -> {
                    response.put("message", responseAccountClient.getMessage());
                    response.put("status", responseAccountClient.getStatus());
                    response.put("body", responseAccountClient.getBody());

                    return Mono.just(ResponseEntity.ok().body(response));
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
