package com.pref.krule.example;

import com.pref.krule.domain.account.service.AccountService;
import com.pref.krule.domain.account.web.dto.request.SignUpRequest;
import com.pref.krule.global.utils.AccountTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringBootTestDefault {

    // service
    @Autowired
    private AccountService accountService;


    // test util
    @Autowired
    private AccountTestUtil accountTestUtil;

    @BeforeEach
    void before() {
        SignUpRequest signupRequest = accountTestUtil.getSignupForm("test");
        accountService.createNewAccount(signupRequest);
    }

    @Test
    void getAccount() {

        // Given
        Long accountId = 1L;

        // When
//        Account account = accountService.getAccount(accountId);

        // Then
//        assertThat(account)
//                .isNotNull();
    }
}
