package com.services.account.controller;

import com.services.account.model.Account;
import com.services.account.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin("http://localhost:8080")
public class AccountController {

    @Autowired
    IAccountService accountservice;

    //@RequestMapping(value = "accounts", method = RequestMethod.GET)
    @GetMapping(value = "/accounts/{id}")
    Account AccountList(@PathVariable Long id) {
        return accountservice.findById(id);
    }

    @GetMapping(value = "/accounts")
    List<Account> AccountList() {
        return accountservice.findAll();
    }

    @DeleteMapping(value = "/accounts/{id}")
    void removeAccount(@PathVariable Long id){
        accountservice.deleteById(id);
    }

    @PostMapping(value = "/accounts")
    public ResponseEntity<Object> saveAccount(@RequestBody Account Account){
        Account savedAccount = accountservice.save(Account);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAccount.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "accounts/verify/{id}/{creditAmount}/{creditTermAmount}")
    public boolean checkCustomerPosition(@PathVariable Long id, @PathVariable BigDecimal creditAmount, @PathVariable BigDecimal creditTermAmount){
        return accountservice.checkCustomerPosition(id, creditAmount, creditTermAmount);
    }
}
