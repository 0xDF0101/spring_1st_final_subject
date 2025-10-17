package com.example.demo.price.formatter;

import com.example.demo.price.dto.Price;


public class KoreanOutputFormatter implements OutPutFormatter {

    public String format(Price price, int usage) {
        if(price == null) {
            return "해당 내용이 없습니다.";
        }
        return String.format("지자체명: %s, 업종: %s, 구간금액(원): %d, 총금액(원): %d",
                price.getCity(), price.getSector(), price.getUnitPrice(), price.getUnitPrice()*usage);
    }
}
