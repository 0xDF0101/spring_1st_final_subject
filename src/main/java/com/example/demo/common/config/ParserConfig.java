package com.example.demo.common.config;

import com.example.demo.common.dataparser.CsvDataParser;
import com.example.demo.common.dataparser.DataParser;
import com.example.demo.common.dataparser.JsonDataParser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParserConfig {

//    @Bean
//    DataParser jsonDataParser() {
//        return new JsonDataParser();
//    }
//
//    @Bean
//    DataParser csvDataParser() {
//        return new CsvDataParser();
//    }

}
