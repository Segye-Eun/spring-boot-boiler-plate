package com.pref.krule.global.api;

import lombok.Data;

import java.util.Map;

/**
 * packageName      : com.template.basespring.global.api
 * fileName          : RestApiResponse
 * author           : ryan
 * date             : 2022/09/27
 * description      :
 * =====================================================
 * DATE               AUTHOR                NOTE
 * -----------------------------------------------------
 * 2022/09/27          ryan       최초 생성
 */
@Data
public class RestApiResponse {
    private boolean success;
    private Map<String, Object> data;

    public static RestApiResponse createResponse(boolean success, Map<String, Object> data) {
        RestApiResponse restApiResponse = new RestApiResponse();
        restApiResponse.setSuccess(success);
        restApiResponse.setData(data);
        return restApiResponse;
    }
}
