package com.example.demo.price.service;

import com.example.demo.common.dataparser.JsonDataParser;
import com.example.demo.price.dto.Price;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PriceService {

    private JsonDataParser jsonDataParser;
    @Autowired
    public PriceService(JsonDataParser jsonDataParser) {
        this.jsonDataParser = jsonDataParser;
    }

    public List<String> cities() {
        List<String> cities = jsonDataParser.cities();
        cities = cities.stream().distinct().collect(Collectors.toList()); // 중복 제거 코드
        return cities;
    }

    public List<String> sectors(String city) {
        return null;
    }

    public Price price(String city, String sector) {
        return null;
    }

    public String billTotal(String city, String sector, int usage) {
        return null;
    }

}
