package ru.netrax.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class SoftwareEngineer {
    String id;
    String task;

    public SoftwareEngineer(String task) {
        this.id = UUID.randomUUID().toString();
        this.task = task;
    }
}
