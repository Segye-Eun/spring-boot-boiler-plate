package com.pref.krule.global.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pref.krule.global.exception.dto.CommonException;
import com.pref.krule.global.exception.dto.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class RestApiController {

    private final ObjectMapper objectMapper;

    public RestApiController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // Case 1. Fail (Spring Security Exception)

    // Case 2. Fail (Valid Error)
    //  return
    //      success: false,
    //      data: {
    //          getField(): getDefaultMessage(),
    //          ...
    //      }
    protected ResponseEntity<String> createFailRestResponse(Map<String, Object> data) {
        RestApiResponse response = RestApiResponse.createResponse(false, data);
        return convertToResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, response);
    }

    protected ResponseEntity<String> createFailRestResponse(BindingResult result) {
        Map<String, Object> data = new HashMap<>() {{
            put("error", result);
        }};
        RestApiResponse response = RestApiResponse.createResponse(false, data);
        return convertToResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, response);
    }


    // Case 4. Success
    //  return
    //      success: true,
    //      data: {
    //          account: {
    //              email: "hgd@gmail.com",
    //              name: "홍길동",
    //              ...
    //          },
    //          ...
    //      }
    protected ResponseEntity<String> createRestResponse(Map<String, Object> data) {
        RestApiResponse restApiResponse = RestApiResponse.createResponse(true, data);
        return convertToResponseEntity(HttpStatus.OK, restApiResponse);
    }

    private ResponseEntity<String> convertToResponseEntity(HttpStatus status, RestApiResponse restApiResponse) {
        String responseBody;
        try {
            responseBody = objectMapper.writeValueAsString(restApiResponse);
        } catch (JsonProcessingException exception) {
            throw new CommonException(ErrorCode.JSON_PROCESS_FAIL, exception);
        }
        return ResponseEntity.status(status).body(responseBody);
    }
}
