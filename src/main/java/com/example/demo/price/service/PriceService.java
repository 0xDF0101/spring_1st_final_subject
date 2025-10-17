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
        return jsonDataParser.cities();
    }

    public List<String> sectors(String city) {
        return jsonDataParser.sectors(city);
    }

    public Price price(String city, String sector) {
        return jsonDataParser.price(city, sector);
    }

    public String billTotal(String city, String sector, int usage) {
        return null;
    }

}
