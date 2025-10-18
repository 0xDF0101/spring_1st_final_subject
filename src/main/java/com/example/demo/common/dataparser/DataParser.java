package com.example.demo.common.dataparser;

import com.example.demo.account.dto.Account;
import com.example.demo.price.dto.Price;

import java.io.IOException;
import java.util.List;

public interface DataParser {

    <T> List<T> loadData(String fileName, Class<T> type) throws IOException; // ⭐️ 추가

    List<String> cities() throws IOException;

    List<String> sectors(String city);

    Price price(String city, String sector);

    List<Account> accounts() throws IOException;
}
