package com.pref.krule.domain.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pref.krule.global.api.RestApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "[99]. Main Controller")
@Validated
@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController extends RestApiController {

    public MainController(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    // =================================================================================================================

    @ApiOperation(value = "Server Check")
    @GetMapping(value = "/server-check")
    public ResponseEntity<String> checkServerStatus() {

        Map<String, Object> response = new HashMap<>() {{
            put("server", "Server OK");
        }};

        return createRestResponse(response);
    }
}
