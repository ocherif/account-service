package com.services.account.service;

import com.services.account.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface IAccountService {
    List<Account> findAll();
    Account findById(Long id);
    void deleteById(Long id);
    Account save(Account account);
    boolean checkCustomerPosition(Long accountId, BigDecimal loanAmount, BigDecimal creditTermAmount);
}
