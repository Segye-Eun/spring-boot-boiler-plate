package com.pref.krule.domain.account.web.dto.request;

import com.pref.krule.domain.account.entity.enums.AccountType;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SignUpRequest {

    @NotNull
    private AccountType accountType;

    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordConfirm;
    @NotBlank
    private String name;
    @NotBlank
    private String nickname;
    @NotBlank
    private String phone;
    @NotNull
    private boolean kakaoAuthAgreement;
    @NotNull
    private boolean marketingAgreement;

}
