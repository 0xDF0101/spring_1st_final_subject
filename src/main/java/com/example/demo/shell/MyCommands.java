package com.example.demo.shell;

import com.example.demo.account.dto.Account;
import com.example.demo.account.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/*
* 여기다가 shell의 응답을 적나보다
*
* */

@ShellComponent
@Slf4j
public class MyCommands {

    AuthenticationService authenticationService;

    @Autowired // 의존성 주입
    public MyCommands(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @ShellMethod
    public String greet(String name) {
        return "hiiiiii" + name;
    }

    @ShellMethod
    public String login(long id, String password) {

        Account account = authenticationService.login(id, password);
//        log.info("id: {}", account.getId());
//        log.info("pw: {}", account.getPassword());
//        log.info("name: {}", account.getName());
        if(account == null) {
            return "잘못된 id나 pw입니다.";
        }

        return String.format("Account(id=%d, pw=%s, name=%s)",account.getId(), account.getPassword(), account.getName());
    }

    @ShellMethod
    public String logout() {
        if(authenticationService.logout() == null) {
            return "이미 로그아웃 상태입니다.";
        } else {
            return "Good Bye ^~^";
        }
    }

    @ShellMethod
    public String currentUser() {
        Account account = authenticationService.getCurrentAccount();
        if(account == null) {
            return "로그아웃 상태입니다.";
        }
        return String.format("Account(id=%d, pw=%s, name=%s)",account.getId(), account.getPassword(), account.getName());
    }

    @ShellMethod
    public String city() {
        return null;
    }

    @ShellMethod
    public String sector(String city) {
        return null;
    }

    @ShellMethod
    public String price(String city, String sector) {
        return null;
    }

    @ShellMethod
    public String billTotal(String city, String sector, int usage) {
        return null;
    }


}