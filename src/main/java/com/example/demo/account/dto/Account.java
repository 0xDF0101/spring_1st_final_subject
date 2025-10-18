package com.example.demo.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {

    @CsvBindByName(column = "아이디")
    @JsonProperty("아이디")
    long id;

    @CsvBindByName(column = "비밀번호")
    @JsonProperty("비밀번호")
    String password;

    @CsvBindByName(column = "이름")
    @JsonProperty("이름")
    String name;
}
