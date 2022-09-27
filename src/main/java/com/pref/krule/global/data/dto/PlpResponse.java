package com.pref.krule.global.data.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PlpResponse {

    private int totalPageNum;           // 전체 페이지 총 개수
    private int currentPageNum;         // 현재 페이지 위치
    private int currentElementNum;      // 현재 페이지 Space 개수

    private boolean isFirst;            // 첫번째 페이지 확인자
    private boolean isLast;             // 마지막 페이지 확인자

    List<?> content;             // 현재 페이지 컨텐츠

    public static PlpResponse createPlpResponse(Page<?> pageValue) {
        PlpResponse plpResponse = new PlpResponse();
        plpResponse.setTotalPageNum(pageValue.getTotalPages());
        plpResponse.setCurrentPageNum(pageValue.getNumber());
        plpResponse.setCurrentElementNum(pageValue.getNumberOfElements());
        plpResponse.setFirst(pageValue.isFirst());
        plpResponse.setLast(pageValue.isLast());
        plpResponse.setContent(pageValue.getContent());
        return plpResponse;
    }
}
