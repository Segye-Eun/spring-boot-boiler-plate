package com.pref.krule.domain.account.web.validator;

import com.pref.krule.domain.account.repository.AccountRepository;
import com.pref.krule.domain.account.web.dto.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * packageName      : com.template.basespring.domain.account.web.validator
 * fileName          : SignUpValidator
 * author           : ryan
 * date             : 2022/09/27
 * description      :
 * =====================================================
 * DATE               AUTHOR                NOTE
 * -----------------------------------------------------
 * 2022/09/27          ryan       최초 생성
 */
@Component
@RequiredArgsConstructor
public class SignUpValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SignUpRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignUpRequest request = (SignUpRequest) target;

        if (accountRepository.existsByEmail(request.getEmail())) {
            errors.rejectValue("email", "invalid.email", "This email already exists");
        }

        if (accountRepository.existsByNickname(request.getNickname())) {
            errors.rejectValue("nickname", "invalid.nickname", "This Nickname already exists");
        }

        if (!request.getPassword().equals(request.getPasswordConfirm())) {
            errors.rejectValue("passwordConfirm", "invalid.passwordConfirm", "Password Confirm incorrect");
        }

    }
}
