package com.everis.fixedaccountservice.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AccountClientBean
{
    private String idAccount;
    private String accountNumber;
    private String type;
    private BigDecimal amountTotal;

}