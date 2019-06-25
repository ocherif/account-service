package com.services.account;

import com.services.account.model.Account;
import com.services.account.repository.AccountRepository;
import com.services.account.service.AccountService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class AccountServiceTest {

    @InjectMocks
    AccountService accountService;

    @Mock
    AccountRepository accountRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        Account account1 = new Account.AccountBuilder()
                .withCustomerName("Toto")
                .withPosition(BigDecimal.valueOf(50000))
                .build();

        Account account2 = new Account.AccountBuilder()
                .withCustomerName("Tata")
                .withPosition(BigDecimal.valueOf(20000))
                .build();

        Mockito.when(accountRepository.findAll()).thenReturn(Arrays.asList(new Account[] {account1, account2}));

        MatcherAssert.assertThat(accountService.findAll(), hasSize(2));
    }

    @Test
    public void testFindById() {
        Account account = new Account.AccountBuilder()
                .withCustomerName("Toto")
                .withPosition(new BigDecimal(50000))
                .build();

        Mockito.when(accountRepository.getOne(1L)).thenReturn(account);

        Account resultProduct = accountService.findById(1L);

        assertThat(resultProduct, Matchers.equalTo(account));

        verify(accountRepository).getOne(1L);
    }

    @Test
    public void testCreditRequestRefused(){
        Account account = new Account.AccountBuilder()
                .withCustomerName("Toto")
                .withPosition(new BigDecimal(50000))
                .build();

        Mockito.when(accountRepository.getOne(1L)).thenReturn(account);

        assertThat(accountService.checkCustomerPosition(1L, new BigDecimal(10000), new BigDecimal(100)), is(false));
    }

    @Test
    public void testCreditRequestAccepted(){
        Account account = new Account.AccountBuilder()
                .withCustomerName("Toto")
                .withPosition(new BigDecimal(50000))
                .build();

        Mockito.when(accountRepository.getOne(1L)).thenReturn(account);

        assertThat(accountService.checkCustomerPosition(1L, new BigDecimal(2000), new BigDecimal(100)), is(true));
    }
}
