package com.pref.krule.domain.account.entity.embedded;

import javax.persistence.Embeddable;

/**
 * packageName      : com.template.basespring.domain.account.entity.embedded
 * fileName          : Address
 * author           : ryan
 * date             : 2022/09/27
 * description      :
 * =====================================================
 * DATE               AUTHOR                NOTE
 * -----------------------------------------------------
 * 2022/09/27          ryan       최초 생성
 */
@Embeddable
public class Address {

    private String zoneCode;
    private String address;
    private String detailAddress;

}
