package org.example.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

//@Data
public class Sportsman {
    private UUID id;

    private String fio;

    private int age;

    private LocalDate birthday;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

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
