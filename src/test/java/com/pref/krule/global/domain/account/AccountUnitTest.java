package com.pref.krule.global.domain.account;

import com.pref.krule.domain.account.web.validator.assertion.AccountAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountUnitTest {

    @Test
    @DisplayName("Runtime Assert Test")
    void assertTest() {
        AccountAssert.hasNotification(2L);
    }
}
