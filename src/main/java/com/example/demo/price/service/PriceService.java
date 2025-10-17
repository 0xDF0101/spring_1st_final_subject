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
        Price price = jsonDataParser.price(city, sector);
        if(price == null) {
            return "해당 내용이 없습니다.";
        }
        return String.format("지자체명: %s, 업종: %s, 구간금액(원): %d, 총금액(원): %d",
                price.getCity(), price.getSector(), price.getUnitPrice(), price.getUnitPrice()*usage);
    }

}
