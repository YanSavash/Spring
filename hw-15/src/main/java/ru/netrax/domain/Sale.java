package ru.netrax.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Sale {
    private Map<String, List<String>> orderList = new HashMap<>();

    public Sale(String name, List<String> orderList) {
        this.orderList.put(name, orderList);
    }
}
