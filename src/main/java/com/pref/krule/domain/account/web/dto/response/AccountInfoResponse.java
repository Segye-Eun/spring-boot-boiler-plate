package com.pref.krule.domain.account.web.dto.response;

import com.pref.krule.domain.account.entity.Account;
import com.pref.krule.domain.account.entity.embedded.Address;
import com.pref.krule.domain.account.entity.enums.AccountType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AccountInfoResponse {

    private Long id;
    private AccountType accountType;
    private String email;
    private String name;
    private String phone;

    private Address homeAddress;

    private boolean marketingAgreement;

    private LocalDateTime joinedAt;
    private LocalDateTime deletedAt;
    private LocalDateTime passwordModifiedAt;

    public static AccountInfoResponse getAccountProfile(Account account) {
        return AccountInfoResponse.builder()
                .id(account.getId())
                .accountType(account.getAccountType())
                .email(account.getEmail())
                .name(account.getName())
                .phone(account.getPhone())
                .homeAddress(account.getHomeAddress())
                .marketingAgreement(account.isMarketingAgreement())
                .deletedAt(account.getDeletedAt())
                .joinedAt(account.getJoinedAt())
                .passwordModifiedAt(account.getPasswordModifiedAt())
                .build();
    }
}
