package com.pref.krule.global.annotation.constraints;

import com.pref.krule.global.data.security.UserAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseAccountValidator {

    protected Long getAccountId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount userAccount = (UserAccount) authentication.getPrincipal();
        return userAccount.getAccount().getId();
    }
}
