package com.pref.krule.global.utils.kakao.dto;

import lombok.Data;

@Data
public class KakaoAuthDto {

    String code;
    String state;

    String error;
    String error_description;
}
