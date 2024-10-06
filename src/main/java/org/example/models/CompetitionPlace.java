package org.example.models;

import lombok.Data;

@Data
public class CompetitionPlace {
    private String country;

    private String city;

    private String coordinates;

    public CompetitionPlace(String country, String city, String coordinates) {
        this.country = country;
        this.city = city;
        this.coordinates = coordinates;
    }
}
