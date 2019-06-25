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
    @GetMapping(value = "/accounts/{customerId}")
    Account AccountList(@PathVariable Long customerId) {
        return accountservice.findById(customerId);
    }

    @GetMapping(value = "/accounts")
    List<Account> AccountList() {
        return accountservice.findAll();
    }

    @DeleteMapping(value = "/accounts/{customerId}")
    void removeAccount(@PathVariable Long customerId){
        accountservice.deleteById(customerId);
    }

    @PostMapping(value = "/accounts")
    public ResponseEntity<Object> saveAccount(@RequestBody Account Account){
        Account savedAccount = accountservice.save(Account);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{customerId}")
                .buildAndExpand(savedAccount.getCustomerId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "accounts/verify/{customerId}/{creditAmount}/{creditTermAmount}")
    public boolean checkCustomerPosition(@PathVariable Long customerId, @PathVariable BigDecimal creditAmount, @PathVariable BigDecimal creditTermAmount){
        return accountservice.checkCustomerPosition(customerId, creditAmount, creditTermAmount);
    }
}
