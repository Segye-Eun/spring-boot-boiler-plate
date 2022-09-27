package com.pref.krule.domain.account.entity;

import com.pref.krule.domain.account.entity.embedded.Address;
import com.pref.krule.domain.account.entity.enums.AccountRank;
import com.pref.krule.domain.account.entity.enums.AccountRole;
import com.pref.krule.domain.account.entity.enums.AccountType;
import com.pref.krule.domain.account.web.dto.request.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@AllArgsConstructor
@SequenceGenerator(name = "account_seq", sequenceName = "account_seq", initialValue = 1, allocationSize = 1)
@NoArgsConstructor(access = PROTECTED)
@Builder(access = PRIVATE)
public class Account {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "account_seq")
    @Column(name = "account_id")
    private Long id;

    @Enumerated(value = STRING)
    private AccountType accountType;

    @Column(unique = true)
    private String email;
    private String password;

    private String name;

    @Column(unique = true)
    private String nickname;

    @Embedded
    private Address homeAddress;

    @Column(unique = true)
    private String phone;

    @Enumerated(value = STRING)
    private AccountRole accountRole;

    @Enumerated(value = STRING)
    private AccountRank accountRank;

    private String kakaoUserId;

    private boolean kakaoAuth;

    private boolean active;

    private LocalDateTime joinedAt;

    private LocalDateTime deletedAt;
    private LocalDateTime passwordModifiedAt;

    private boolean marketingAgreement;

    // password
    public static Account createAccount(SignUpRequest signUpRequest) {
        return Account.builder()
                .accountType(signUpRequest.getAccountType())
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .name(signUpRequest.getName())
                .phone(signUpRequest.getPhone())
                .kakaoAuth(signUpRequest.isKakaoAuthAgreement())
                .marketingAgreement(signUpRequest.isMarketingAgreement())
                .build();
    }

    // kakao
    public static Account createAccount(KakaoSignUpRequest kakaoSignUpRequest) {
        return Account.builder()
                .accountType(kakaoSignUpRequest.getAccountType())
                .email(kakaoSignUpRequest.getEmail())
                .password(kakaoSignUpRequest.getPassword())
                .name(kakaoSignUpRequest.getName())
                .kakaoAuth(kakaoSignUpRequest.isKakaoAuthAgreement())
                .kakaoUserId(kakaoSignUpRequest.getKakaoUserId())
                .build();
    }

    //    Aggregate
    public void completeSignUp() {
        this.active = true;
        this.joinedAt = LocalDateTime.now();
        this.passwordModifiedAt = null;
    }

    public void modifyPassword(String newPassword) {
        setPassword(newPassword);
        setPasswordModifiedAt(LocalDateTime.now());
    }

    public void setUserInActive(boolean active) {
        setActive(false);
        setDeletedAt(LocalDateTime.now());
    }

    //    Private Setters
    private void setActive(boolean active) {
        this.active = active;
    }

    private void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    private void setPasswordModifiedAt(LocalDateTime passwordModifiedAt) {
        this.passwordModifiedAt = passwordModifiedAt;
    }

    private void setPassword(String password) {
        this.password = password;
    }

}
