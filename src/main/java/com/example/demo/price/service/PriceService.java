package com.example.demo.price.service;

import com.example.demo.common.dataparser.DataParser;
import com.example.demo.common.dataparser.JsonDataParser;
import com.example.demo.price.dto.Price;
import com.example.demo.price.formatter.EnglishOutputFormatter;
import com.example.demo.price.formatter.KoreanOutputFormatter;
import com.example.demo.price.formatter.OutPutFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class PriceService {

//    private final JsonDataParser jsonDataParser;
    private final DataParser dataParser;
    private final OutPutFormatter outPutFormatter;

//    @Autowired
//    public PriceService(JsonDataParser jsonDataParser,
//                        KoreanOutputFormatter koreanOutputFormatter) {
//        this.jsonDataParser = jsonDataParser;
//        this.
//    }

    public List<String> cities() {
        return dataParser.cities();
    }

    public List<String> sectors(String city) {
        return dataParser.sectors(city);
    }

    public Price price(String city, String sector) {
        return dataParser.price(city, sector);
    }

    public String billTotal(String city, String sector, int usage) {
        Price price = dataParser.price(city, sector);

        return outPutFormatter.format(price, usage);
    }
}
