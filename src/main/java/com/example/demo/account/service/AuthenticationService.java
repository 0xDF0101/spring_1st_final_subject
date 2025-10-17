package com.example.demo.account.service;

import com.example.demo.account.dto.Account;
import com.example.demo.common.dataparser.DataParser;
import com.example.demo.common.dataparser.JsonDataParser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class AuthenticationService {

    @Getter
    private Account currentAccount; // 현재 로그인 된 계정 정보를 담음
    private DataParser dataParser;
    private List<Account> accounts;

    @Autowired
    public AuthenticationService(DataParser dataParser) throws IOException {
        this.dataParser = dataParser;
        accounts = dataParser.accounts();
    }

    public Account login(Long id, String password) {

        for(Account account : accounts) {
            if(account.getId() == id && account.getPassword().equals(password)) { // id, pw 일치
                currentAccount = account; // 현재 계정 정보
                return account; // 이걸 왜 넘겨주는거임?
            }
        }
        return null; // 일치하는 계정이 없을 경우 null 반환
        // ----------> 이렇게 넘겨주면 안될듯!
        // ---> 반환값을 Optional로 해주면 됨
        // 시간 날때 하기 지금은 귀찮
    }

    public Account logout() {
        Account returnAccount;
        if(currentAccount==null) { // 이미 로그아웃 상태라면
            return null;
        } else {
            returnAccount = currentAccount;
            currentAccount = null; // 현재 계정 정보를 비워놓음
            return returnAccount;
        }
    }

}
