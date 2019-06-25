package com.services.account.repository;

import com.services.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAll();
    Account getOne(Long id);
    void deleteById(Long id);
    Account save(Account Account);
}
