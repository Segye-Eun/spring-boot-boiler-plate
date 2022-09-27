package com.pref.krule.domain.account.service;

import com.pref.krule.domain.account.entity.Account;
import com.pref.krule.domain.account.entity.enums.AccountRole;
import com.pref.krule.domain.account.repository.AccountRepository;
import com.pref.krule.domain.account.web.dto.request.KakaoSignUpRequest;
import com.pref.krule.domain.account.web.dto.request.SignUpRequest;
import com.pref.krule.domain.account.web.dto.response.AccountInfoResponse;
import com.pref.krule.global.data.security.UserAccount;
import com.pref.krule.global.exception.dto.CommonException;
import com.pref.krule.global.exception.dto.enums.ErrorCode;
import com.pref.krule.global.utils.kakao.KakaoOAuth;
import com.pref.krule.global.utils.kakao.dto.KakaoAccountInfoDto;
import com.pref.krule.global.utils.kakao.dto.KakaoAuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService implements UserDetailsService {

    //    Repository
    private final AccountRepository accountRepository;

    //    Utils
    private final PasswordEncoder passwordEncoder;
    private final KakaoOAuth kakaoOAuth;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            throw new CommonException(ErrorCode.ACCOUNT_NOT_FOUND);
        }
        return new UserAccount(account);
    }

    @Transactional
    public AccountInfoResponse signUpProcess(SignUpRequest signUpRequest) {
        Account account = createNewAccount(signUpRequest);
        return AccountInfoResponse.getAccountProfile(account);
    }


    @Transactional
    public Account createNewAccount(SignUpRequest signUpRequest) {
        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        Account newAccount = Account.createAccount(signUpRequest);

        newAccount.completeSignUp();
        return accountRepository.save(newAccount);
    }

    @Transactional
    public Account createNewAccount(KakaoSignUpRequest kakaoSignUpRequest) {
        kakaoSignUpRequest.setPassword(passwordEncoder.encode(kakaoSignUpRequest.getPassword()));
        Account newAccount = Account.createAccount(kakaoSignUpRequest);

        newAccount.completeSignUp();
        return accountRepository.save(newAccount);
    }


    // R
    public void login(Account account, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                new UserAccount(account),
                password,
                List.of(new SimpleGrantedAuthority(AccountRole.ROLE_USER.name()))
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * @Author : Ryan
     * @Summary : Kakao 전용 login
     * @Param : KakaoAuthDto kakaoAuthDto
     * @Memo : KakaoAuthDto의 경우 Kakao에서 제공하는 DTO
     * - ryan : Rest API에서 반환할 jwt Token 생성을 위해 반환 타입 변경 (void - > return account)
     **/
    @Transactional
    public Account loginForKakao(KakaoAuthDto kakaoAuthDto) {

        // 1. Get Access Token
        String accessToken = kakaoOAuth.getAccessToken(kakaoAuthDto);

        // 2. Get Kakao Account
        KakaoAccountInfoDto kakaoInfo = kakaoOAuth.getKakaoInfo(accessToken);

        // 3. Check Exist Account & Sign Up
        Account target = accountRepository.findByEmail(kakaoInfo.getEmail());
        if (target == null) {
            KakaoSignUpRequest kakaoSignUpRequest = KakaoSignUpRequest.createKakaoSignUpRequest(kakaoInfo);
            target = createNewAccount(kakaoSignUpRequest);
        }
        login(target, accessToken);
        return target;
    }


}
