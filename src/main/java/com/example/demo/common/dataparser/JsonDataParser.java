package com.example.demo.common.dataparser;

import com.example.demo.account.dto.Account;
import com.example.demo.price.dto.Price;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class JsonDataParser {

    public List<String> cities() {
        return null;
    }

    public List<String> sectors(String city) {
        return null;
    }

    public Price price(String city, String sector) {
        return null;
    }

    public List<Account> accounts() throws IOException {

        JSONParser parser = new JSONParser();
        List<Account> accounts = new ArrayList<>();

        try {
            File file = new ClassPathResource("acount.json").getFile();

            try (Reader reader = new FileReader(file)) {
                JSONArray jsonArray= (JSONArray) parser.parse(reader);
                for(Object obj : jsonArray) {
                    JSONObject user = (JSONObject) obj;

                    long id = (long) user.get("아이디");
                    String pw = (String) user.get("비밀번호");
                    String name = (String) user.get("이름");

                    accounts.add(new Account(id, pw, name));
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
