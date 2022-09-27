package com.pref.krule.domain.account.web.dto.request;

import com.pref.krule.domain.account.entity.Account;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProfileRequest {

    @NotBlank
    private String phone;

    public static ProfileRequest loadProfile(Account account) {
        ProfileRequest profileRequest = new ProfileRequest();
        profileRequest.setPhone(account.getPhone());
        return profileRequest;
    }
}
