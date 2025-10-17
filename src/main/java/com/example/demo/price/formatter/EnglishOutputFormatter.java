package com.example.demo.price.formatter;

import com.example.demo.price.dto.Price;


public class EnglishOutputFormatter implements OutPutFormatter{

    public String format(Price price, int usage) {
        if(price == null) {
            return "There is no such content.";
        }
        return String.format("city: %s, sector: %s, unit price(won): %d, bill total(won): %d",
            price.getCity(), price.getSector(), price.getUnitPrice(), price.getUnitPrice()*usage);
    }
}
