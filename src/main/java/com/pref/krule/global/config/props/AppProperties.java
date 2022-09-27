package com.pref.krule.global.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * packageName      : com.pref.krule.global.config.props
 * fileName          : AppProperties
 * author           : ryan
 * date             : 2022/09/27
 * description      :
 * =====================================================
 * DATE               AUTHOR                NOTE
 * -----------------------------------------------------
 * 2022/09/27          ryan       최초 생성
 */
@Data
@Component
@ConfigurationProperties("app.props.host")
public class AppProperties {
    private String url;
}
