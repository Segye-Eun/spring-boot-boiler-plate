package com.pref.krule.global.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pref.krule.domain.account.entity.Account;
import com.pref.krule.global.event.log.dto.ExceptionEvent;
import com.pref.krule.global.exception.dto.CommonException;
import org.springframework.context.ApplicationEventPublisher;

public class RestApiControllerAdvice extends RestApiController {

    private final ApplicationEventPublisher eventPublisher;

    public RestApiControllerAdvice(ObjectMapper objectMapper, ApplicationEventPublisher eventPublisher) {
        super(objectMapper);
        this.eventPublisher = eventPublisher;
    }

    protected void sendLogEvent(CommonException aException, Account account) {
        eventPublisher.publishEvent(ExceptionEvent.createExceptionEvent(aException, account));
    }

    // TODO : Error Email Logic 미구현 - Unexpected Logic
    protected void sendEmailLogEvent(CommonException aException, Account account) {

    }
}
