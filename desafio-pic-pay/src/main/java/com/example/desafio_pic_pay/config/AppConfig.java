package com.example.desafio_pic_pay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig
{
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
