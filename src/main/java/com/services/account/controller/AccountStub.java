package com.services.account.controller;

import com.services.account.model.Account;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class AccountStub {

    private static List<Account> accounts;

    static {
        Account account1 = new Account.AccountBuilder(1)
                .withCustomerName("Toto")
                .withPosition(new BigDecimal(50000))
                .build();

        Account account2 = new Account.AccountBuilder(1)
                .withCustomerName("Tata")
                .withPosition(new BigDecimal(10000))
                .build();

        accounts = Arrays.asList(new Account [] {account1, account2});
    }

    protected static List<Account> getAccounts(){
        return accounts;
    }
}
