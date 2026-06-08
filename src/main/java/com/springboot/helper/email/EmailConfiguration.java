package com.springboot.helper.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Properties;

/**
 * - 구글 SMTP 서버를 이용하기 위한 설정
 * - EmailSendable 인터페이스의 구현 클래스를 Bean object로 등록한다.
 * - JavaMailSender 인터페이스의 구현 클래스를 Bean object로 등록한다.
 */
@Configuration
public class EmailConfiguration {
    /**
     * application-local.yml 파일에서 구글 SMTP 설정 정보를 읽어들여서 아래의 필드 변수에 값으로 추가한다.
     */
    @Value("${mail.smtp.host}")
    private String host;

    @Value("${mail.smtp.port}")
    private int port;

    @Value("${mail.smtp.username}")
    private String username;

    @Value("${mail.smtp.password}")
    private String password;

    @Value("${mail.smtp.auth}")
    private String auth;

    @Value("${mail.smtp.starttls.enable}")
    private String tlsEnable;

    @Bean
    public EmailSendable mockExceptionEmailSendable() {
        return new MockExceptionEmailSendable();
    }

//    @Primary
    @Bean
    public EmailSendable simpleEmailSendable() {
        return new SimpleEmailSendable(javaMailSender());
    }


    @Primary
    @Bean
    public EmailSendable templateEmailSendable(TemplateEngine templateEngine) {
        return new TemplateEmailSendable(javaMailSender(), templateEngine, new Context());
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", tlsEnable);

        return mailSender;
    }
}