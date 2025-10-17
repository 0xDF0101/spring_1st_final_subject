package com.example.demo.shell;

import com.example.demo.account.dto.Account;
import com.example.demo.account.service.AuthenticationService;
import com.example.demo.price.dto.Price;
import com.example.demo.price.service.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

/*
* 여기다가 shell의 응답을 적나보다
*
* */

@ShellComponent
@Slf4j
public class MyCommands {

    AuthenticationService authenticationService;
    PriceService priceService;

    @Autowired // 의존성 주입
    public MyCommands(AuthenticationService authenticationService,
                      PriceService priceService) {
        this.authenticationService = authenticationService;
        this.priceService = priceService;
    }

    @ShellMethod
    public String greet(String name) {
        return "hiiiiii" + name;
    }

    @ShellMethod
    public String login(long id, String password) {

        Account account = authenticationService.login(id, password);
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

        List<String> cities = priceService.cities();

        return cities.toString();
    }

    @ShellMethod
    public String sector(String city) {
        List<String> types = priceService.sectors(city);
        if(types.isEmpty()) {  // null체크를 하는게 맞나?
            return "도시 이름을 잘못 입력하셨습니다.";
        } else {
            return types.toString();
        }
    }

    @ShellMethod
    public String price(String city, String sector) {
        Price price = priceService.price(city, sector);
        if(price == null) {
            return "해당 내용이 없습니다.";
        }
        return String.format("Price(id=%d, city=%s, sector=%s, unitPrice=%d)",
                price.getId(), price.getCity(), price.getSector(), price.getUnitPrice());
    }

    @ShellMethod
    public String billTotal(String city, String sector, int usage) {
        return null;
    }


}