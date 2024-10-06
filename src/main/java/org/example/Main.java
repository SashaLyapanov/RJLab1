package org.example;

import org.example.generators.CompetitionGenerator;
import org.example.generators.CompetitoinPlaceGenerator;
import org.example.models.CompetitionPlace;
import org.example.models.CompetitionTypes;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        //        SportsmanGenerator generator = new SportsmanGenerator();
//        generator.generateSportsman(100).stream().forEach(System.out::println);

//        CompetitoinPlaceGenerator cpGenerator = new CompetitoinPlaceGenerator();
//        cpGenerator.getCompetitionPlaces(100).stream().forEach(System.out::println);

//        Random random = new Random();
//        System.out.println(CompetitionTypes.values()[random.nextInt(3)]);
        CompetitionGenerator.generate(100000, 1000).stream().forEach(System.out::println);
    }
}