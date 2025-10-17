package com.example.demo.common.dataparser;

import com.example.demo.account.dto.Account;
import com.example.demo.price.dto.Price;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JsonDataParser {

    private Object getJsonResources(String fileName) {
        JSONParser parser = new JSONParser();
        try {
            File file = new ClassPathResource(fileName).getFile();
            try(Reader reader = new FileReader(file)) {
                return parser.parse(reader);
            } catch (ParseException e) {
                throw new RuntimeException("파싱 중 예외 발생! : " + e);
            }
        } catch (IOException e) {
            throw new RuntimeException("파일 입출력 중 오류 발생! : " + e);
        }
    }

    public List<String> cities() {

        List<String> cities = new ArrayList<>();
        Object jsonData = getJsonResources("price.json");
        if(jsonData instanceof JSONArray){
            JSONArray jsonArray = (JSONArray) jsonData;
            for(Object obj : jsonArray) {
                JSONObject user = (JSONObject) obj;

                String name = (String) user.get("지자체명");
                cities.add(name);
            }
        }
        cities = cities.stream().distinct().collect(Collectors.toList()); // 중복 제거 코드
        return cities;
    }

    public List<String> sectors(String city) {

        List<String> types = new ArrayList<>();
        Object jsonData = getJsonResources("price.json");
        if(jsonData instanceof JSONArray){
            JSONArray jsonArray = (JSONArray) jsonData;
            for(Object obj : jsonArray) {
                JSONObject user = (JSONObject) obj;

                String name = (String) user.get("지자체명");
                if(name.equals(city)) {
                    String type = (String) user.get("업종");
                    types.add(type);
                }
            }
        }
        types = types.stream().distinct().collect(Collectors.toList()); // 중복 제거
        return types;
    }

    public Price price(String city, String sector) {
        return null;
    }

    public List<Account> accounts() throws IOException {

        List<Account> accounts = new ArrayList<>();
        Object jsonData = getJsonResources("account.json");
        if(jsonData instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) jsonData;
            for(Object obj : jsonArray) {
                JSONObject user = (JSONObject) obj;

                String name = (String) user.get("이름");
                long pw = (long) user.get("비밀번호");
                long id = (long) user.get("아이디");

                String strPw = String.valueOf(pw);
                accounts.add(new Account(id, strPw, name));
            }
        } else {
            log.error("account.json은 Array 형태가 아닙니다.");
        }
        return accounts;
    }
}
