package com.pref.krule.domain.account.web.dto.request;

import com.pref.krule.domain.account.entity.Account;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AgreementRequest {

    @NotNull
    private boolean marketingAgreement;

    public static AgreementRequest loadAgreement(Account account) {
        AgreementRequest agreementRequest = new AgreementRequest();
        agreementRequest.setMarketingAgreement(account.isMarketingAgreement());
        return agreementRequest;
    }
}
