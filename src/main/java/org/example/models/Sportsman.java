package org.example.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Sportsman {
    private UUID id;

    private String fio;

    private int age;

    private LocalDate birthday;

    private String mail;

    private Coach coach;

    public Sportsman(UUID id, String fio, int age, LocalDate birthday, String mail, Coach coach) {
        this.id = id;
        this.fio = fio;
        this.age = age;
        this.birthday = birthday;
        this.mail = mail;
        this.coach = coach;
    }
}
