package com.everis.fixedaccountservice.utils;

import com.everis.fixedaccountservice.bean.AccountClientBean;
import com.everis.fixedaccountservice.bean.ResponseAccountClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class Webclients
{
    String endpointCustomerP = "http://localhost:8090/";
    String pathSendAccount = "account/p-customer/";

    private final WebClient client;

    public Webclients(WebClient.Builder builder) {
        this.client = builder.baseUrl(endpointCustomerP).build();
    }

    public Mono<ResponseAccountClient> sendClientAccount(AccountClientBean clientBean, String documentNumber){
        return this.client.post().uri(pathSendAccount.concat(documentNumber))
                .body(Mono.just(clientBean), AccountClientBean.class)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals,
                        clientResponse -> Mono.empty())
                .bodyToMono(ResponseAccountClient.class);
    }
}