package com.example.demo.account.service;

import com.example.demo.account.dto.Account;
import com.example.demo.common.dataparser.JsonDataParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class AuthenticationService {

    private Account currentAccount;
    private JsonDataParser jsonDataParser;
    private List<Account> accounts;

    @Autowired
    public AuthenticationService(JsonDataParser jsonDataParser) throws IOException {
        this.jsonDataParser = jsonDataParser;
        accounts = jsonDataParser.accounts();
    }

    public Account login(Long id, String password) {

        for(Account account : accounts) {

        }


        return null;
    }

    public void logout() {
    }
}
