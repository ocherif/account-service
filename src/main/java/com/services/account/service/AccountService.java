package com.services.account.service;

import com.services.account.model.Account;
import com.services.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService implements IAccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.getOne(id);
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account save(Account Account) {
        return accountRepository.save(Account);
    }

    @Override
    public boolean checkCustomerPosition(Long accountId, BigDecimal loanAmount, BigDecimal creditTermAmount) {
        Account account = accountRepository.getOne(accountId);
        return account.getPosition().compareTo(loanAmount.multiply(BigDecimal.valueOf(10))) > 0;
    }
}
