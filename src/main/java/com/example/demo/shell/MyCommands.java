package com.example.demo.shell;

import com.example.demo.account.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;

/*
* 여기다가 shell의 응답을 적나보다
*
* */

@ShellComponent
public class MyCommands {

    AuthenticationService authenticationService;

    @Autowired
    public MyCommands(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @ShellMethod
    public String greet(String name) {
        return "hiiiiii" + name;
    }

    @ShellMethod
    public String login(long id, String password) {

//        authenticationService(id, password);

        return null;
    }

    @ShellMethod
    public String logout() {
        return null;
    }

    @ShellMethod
    public String currentUser() {
        return null;
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