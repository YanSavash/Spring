package ru.netrax.domain;

import lombok.Data;

import java.util.List;

@Data
public class Client {
    String name;
    List<String> orderList;

    public Client(String name, List<String> orderList) {
        this.name = name;
        this.orderList = orderList;
    }
}
