package com.jfb.minhalivraria.controller.request;

import lombok.Getter;

import java.util.List;

@Getter
public class PeopleRequest {

    private String name;
    private String cpf;
    private List<String> books;
}
