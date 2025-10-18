package com.example.demo.common.dataparser;

import com.example.demo.account.dto.Account;
import com.example.demo.price.dto.Price;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.shell.command.invocation.InvocableShellMethod.log;

@ConditionalOnProperty(name="file.type", havingValue = "csv")
@Component
public class CsvDataParser implements DataParser {


    @Override
    public <T> List<T> loadData(String csvFileName, Class<T> type) {
//        String csvFileName = "price.csv";
        try {
            ClassPathResource resource = new ClassPathResource(csvFileName);
            try(Reader reader = new InputStreamReader(resource.getInputStream())) {
                CSVReader csvReader = new CSVReaderBuilder(reader)
                        .withSkipLines(0)
                        .build();

                CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(csvReader)
                        .withType(type)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withSeparator(',')
                        .build();
                List<T> dataList= csvToBean.parse();
                return dataList;
            }
        } catch(IOException e) {
            log.error("CSV 파일 로드 중 오류 발생 : {}" + csvFileName, e.getMessage(), e);
            return Collections.emptyList();
        } catch(RuntimeException e) {
            log.error("CSV 파일 로드 중 런타임 오류 발생 : {}, {}", csvFileName, e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<String> cities() {
        List<Price> prices = loadData("price.csv", Price.class);
        List<String> cities = new ArrayList<>();

        for(Price price : prices) {
            cities.add(price.getCity().trim());
        }
        cities = cities.stream().distinct().collect(Collectors.toList()); // 중복 제거
        return cities;
    }

    public List<String> sectors(String city) {
        return null;
    }

    public Price price(String city, String sector) {
        return null;
    }

    public List<Account> accounts() throws IOException {
        List<Account> data = loadData("account.csv", Account.class);
        List<Account> accounts = new ArrayList<>();

        for(Account account : data) {
            accounts.add(new Account(account.getId(), account.getPassword().trim(), account.getName().trim()));
        }

        accounts = accounts.stream().distinct().collect(Collectors.toList()); // 중복 제거
        return accounts;
    }
}
