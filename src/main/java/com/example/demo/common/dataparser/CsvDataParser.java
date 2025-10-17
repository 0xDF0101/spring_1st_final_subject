package com.example.demo.common.dataparser;

import com.example.demo.account.dto.Account;
import com.example.demo.price.dto.Price;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConditionalOnProperty(name="file.type", havingValue = "csv")
@Component
public class CsvDataParser implements DataParser{

    public List<Map<String, String>> getCsvResources(String fileName) throws IOException {
        File file;
        try {
            file = new ClassPathResource(fileName).getFile();
        } catch(IOException e) {
            throw new IOException("파일 로드 중 오류 발생 : " + e.getMessage());
        }

        List<Map<String, String>> data = new ArrayList<>();
        try(Reader reader = new FileReader(file)) {

            CSVParser csvParser = new CSVParserBuilder()
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withCSVParser(csvParser)
                    .build();
            // 첫줄 읽어서 헤더에 집어넣기
            String[] header = csvReader.readNext();
            if(header == null) {
                throw new IOException("파일의 헤더가 없는데? : ");
            }
            for (int i = 0; i < header.length; i++) {
                if (header[i] != null) {
                    header[i] = header[i].trim();
                }
            }
            // 나머지 읽기
            List<String[]> records = csvReader.readAll();
            for(String[] record : records) {
                Map<String, String> row = new HashMap<>();
                for(int i=0; i<header.length && i < record.length; i++) {
                    row.put(header[i], record[i].trim());
                }
                data.add(row);
            }

        } catch (CsvException e) {
            throw new RuntimeException("CSV파일 파싱 중 오류 발생 : " + e);
        }

        return data;
    }

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

        List<Map<String, String>> data = getCsvResources("account.csv");
        List<Account> accounts = new ArrayList<>();

        for(Map<String, String> account : data) {
            String pw = account.get("비밀번호");
            String name = account.get("이름");
            long id = Long.parseLong(account.get("아이디"));
            accounts.add(new Account(id, pw, name));
        }
        return accounts;
    }


}
