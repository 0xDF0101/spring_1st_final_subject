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

//    private JSONObject getResources(String fileName) {
//        JSONParser parser = new JSONParser();
//
//    }

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
            File file = new ClassPathResource("account.json").getFile();

            try (Reader reader = new FileReader(file)) {
                JSONArray jsonArray= (JSONArray) parser.parse(reader);
                for(Object obj : jsonArray) {
                    JSONObject user = (JSONObject) obj;

                    String name = (String) user.get("이름");
                    long pw = (long) user.get("비밀번호");
                    long id = (long) user.get("아이디");

                    String strPw = String.valueOf(pw);
                    accounts.add(new Account(id, strPw, name));
                }
            } catch (ParseException e) {
                throw new RuntimeException("파싱 중 예외 발생 : " + e);
            }
        } catch (IOException e) {
            throw new RuntimeException("오류다! : " + e);
        }
        return accounts;
    }
}
