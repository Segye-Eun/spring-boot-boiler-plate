package com.pref.krule.domain.account.web.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PasswordModifyRequest {

    @NotBlank
    private String newPassword;

    @NotBlank
    private String newPasswordConfirm;
}
