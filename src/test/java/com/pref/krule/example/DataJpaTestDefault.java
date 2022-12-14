package com.pref.krule.example;

import com.pref.krule.domain.account.entity.Account;
import com.pref.krule.domain.account.repository.AccountRepository;
import com.pref.krule.global.config.TestConfig;
import com.pref.krule.global.utils.AccountTestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *   @Author : Youn
 *   @Summary : Repository Default
 *   @Memo : DataJpaTest 기본형
 **/
@DataJpaTest
@Import({TestConfig.class, AccountTestUtil.class})   // Bean DI를 위해 설정
public class DataJpaTestDefault {

    // repository
    @Autowired
    private AccountRepository accountRepository;

    // test util
    @Autowired
    private AccountTestUtil accountTestUtil;

    @Test
    void getAccount() { // Repository Test

        // Given
        Account account = accountTestUtil.getTestAccount();

        // When
        Account result = accountRepository.save(account);

        // Then
        assertThat(result)
                .isNotNull()
                .isOfAnyClassIn(Account.class);
    }
}
