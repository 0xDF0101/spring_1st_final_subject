package com.example.demo.common.config;

import com.example.demo.common.dataparser.CsvDataParser;
import com.example.demo.common.dataparser.DataParser;
import com.example.demo.common.dataparser.JsonDataParser;
import com.example.demo.price.dto.Price;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

@Configuration
@Slf4j
public class ParserConfig {

    @Bean
    public List<Price> priceDataList() {
        String csvFileName = "price.csv";
        try {
            ClassPathResource resource = new ClassPathResource(csvFileName);
            try(Reader reader = new InputStreamReader(resource.getInputStream())) {
                CSVReader csvReader = new CSVReaderBuilder(reader)
                        .withSkipLines(0)
                        .build();

                CsvToBean<Price> csvToBean = new CsvToBeanBuilder<Price>(csvReader)
                        .withType(Price.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withSeparator(',')
                        .build();
                List<Price> prices = csvToBean.parse();
                return prices;
            }
        } catch(IOException e) {
            log.error("CSV 파일 로드 중 오류 발생 : {}" + csvFileName, e.getMessage(), e);
            return Collections.emptyList();
        } catch(RuntimeException e) {
            log.error("CSV 파일 로드 중 런타임 오류 발생 : ", csvFileName, e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
