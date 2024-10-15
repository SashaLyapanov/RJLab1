package org.example.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Coach {
    private UUID id;

    private String fio;

    private int age;

    private LocalDate birthday;

    private String mail;

    public Coach(UUID id, String fio, int age, LocalDate birthday, String mail) {
        this.id = id;
        this.fio = fio;
        this.age = age;
        this.birthday = birthday;
        this.mail = mail;
    }

}
