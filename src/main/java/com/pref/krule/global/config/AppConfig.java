package com.pref.krule.global.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pref.krule.global.config.props.JwtProps;
import com.pref.krule.global.config.props.MailProps;
import com.pref.krule.global.utils.kakao.dto.KakaoAccountInfoDto;
import com.pref.krule.global.utils.kakao.dto.deserializer.KakaoAccountInfoDeserializer;
import lombok.RequiredArgsConstructor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    // props
    private final MailProps mailProps;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public JWTVerifier jwtVerifier(JwtProps jwtProps) {
        return JWT
                .require(Algorithm.HMAC256(jwtProps.getSecretkey()))
                .withIssuer(jwtProps.getIssur())
                .build();
    }

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailProps.getHost());
        mailSender.setProtocol(mailProps.getProtocol());
        mailSender.setPort(mailProps.getPort());
        mailSender.setUsername(mailProps.getUsername());
        mailSender.setPassword(mailProps.getPassword());
        mailSender.setDefaultEncoding("utf-8");

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.auth", mailProps.getAuth());
        properties.put("mail.smtp.ssl.enable", mailProps.getEnable());
        properties.put("mail.smtp.ssl.trust", mailProps.getHost());
        properties.put("mail.smtp.starttls.enable", mailProps.getEnable());
        properties.put("mail.smtp.starttls.required", mailProps.getRequired());
        mailSender.setJavaMailProperties(properties);
        return mailSender;
    }

    @Bean
    public RestTemplate getCustomRestTemplate(){
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(2000);
        httpRequestFactory.setReadTimeout(3000);
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(200)
                .setMaxConnPerRoute(20)
                .build();
        httpRequestFactory.setHttpClient(httpClient);
        return new RestTemplate(httpRequestFactory);
    }

    // ObjectMapper??? ???????????? ???????????? ????????? ??????
    // ?????? ????????? ?????? ????????? ???????????? ??????
    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        // ?????? ???????????? ???????????? ?????? ??????
        SimpleModule module = new SimpleModule();
        module.addSerializer(BindingResult.class, new BindingResultSerializer());
        module.addDeserializer(KakaoAccountInfoDto.class, new KakaoAccountInfoDeserializer());

        objectMapper.registerModule(module);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }

    private static class BindingResultSerializer extends JsonSerializer<BindingResult> {
        @Override
        public void serialize(BindingResult result, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeStartArray();
            List<FieldError> fieldErrorGroup = result.getFieldErrors();
            for (FieldError error : fieldErrorGroup) {
                gen.writeStartObject();
                gen.writeStringField(error.getField(), error.getDefaultMessage());
                gen.writeEndObject();
            }
            gen.writeEndArray();
        }
    }
}
