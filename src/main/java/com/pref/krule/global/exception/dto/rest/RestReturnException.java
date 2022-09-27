package com.pref.krule.global.exception.dto.rest;

import com.pref.krule.global.exception.dto.CommonException;

public class RestReturnException extends CommonException {

    public RestReturnException(CommonException commonException) {
        super(commonException.getErrorCode(), commonException.getErrorDetailMsg());
    }
}
