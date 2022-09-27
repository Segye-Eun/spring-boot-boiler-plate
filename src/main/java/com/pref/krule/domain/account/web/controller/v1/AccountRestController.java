package com.pref.krule.domain.account.web.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pref.krule.domain.account.service.AccountService;
import com.pref.krule.domain.account.web.dto.request.SignUpRequest;
import com.pref.krule.domain.account.web.dto.response.AccountInfoResponse;
import com.pref.krule.domain.account.web.validator.SignUpValidator;
import com.pref.krule.global.api.RestApiController;
import com.pref.krule.global.utils.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "[1]. Account")
@RestController
@RequestMapping(value = "/api/v1/account", produces = MediaType.APPLICATION_JSON_VALUE, headers = "X-API-VERSION=1")
public class AccountRestController extends RestApiController {

    // services
    private final AccountService accountService;

    // Validators
    private final SignUpValidator signUpValidator;

    // Utils
    private final JwtUtil jwtUtil;

    public AccountRestController(ObjectMapper objectMapper, AccountService accountService, SignUpValidator signUpValidator, JwtUtil jwtUtil) {
        super(objectMapper);
        this.accountService = accountService;
        this.signUpValidator = signUpValidator;
        this.jwtUtil = jwtUtil;
    }

    @InitBinder(value = "signUpRequest")
    public void signUpValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpValidator);
    }

    // ===== ===== ===== ===== ===== Create Business Method ===== ===== ===== ===== =====
    @ApiOperation(value = "회원 가입")
    @PostMapping(value = "/sign-up")
    public ResponseEntity<String> signUp(@RequestBody @Valid SignUpRequest signUpRequest, BindingResult result) {
        if (result.hasErrors()) {
            return createFailRestResponse(result);
        }

        AccountInfoResponse accountInfoResponse = accountService.signUpProcess(signUpRequest);
        Map<String, Object> response = new HashMap<>() {{
            put("account", accountInfoResponse);
        }};
        return createRestResponse(response);
    }

    // ===== ===== ===== ===== ===== Read Business Method ===== ===== ===== ===== =====

    // ===== ===== ===== ===== ===== Update Business Method ===== ===== ===== ===== =====

    // ===== ===== ===== ===== ===== Remove Business Method ===== ===== ===== ===== =====

}
