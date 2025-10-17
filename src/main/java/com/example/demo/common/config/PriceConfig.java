package com.example.demo.common.config;


import com.example.demo.price.formatter.EnglishOutputFormatter;
import com.example.demo.price.formatter.KoreanOutputFormatter;
import com.example.demo.price.formatter.OutPutFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class PriceConfig {

    // Bean 등록
    @Profile("eng")
    @Bean
    OutPutFormatter englishOutputFormatter() {
        return new EnglishOutputFormatter();
    }
    @Profile("!eng")
    @Bean
    OutPutFormatter koreanOutputFormatter() {
        return new KoreanOutputFormatter();
    }
}
