package org.example;

import org.example.generators.CompetitionGenerator;
import org.example.generators.CompetitionPlaceGenerator;
import org.example.generators.SportsmanGenerator;
import org.example.models.Competition;
import org.example.models.CompetitionPlace;
import org.example.models.CompetitionTypes;
import org.example.models.Sportsman;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Action action = new Action(CompetitionGenerator.generate(10, 20));
        action.soutList();
//        var res1 = action.simpleLoop();
        Map<String, List<Sportsman>> res2 = action.streamLoop();
        System.out.println(res2);
//        var res3 = action.customCollectors();
//        if (res1.equals(res2) && res2.equals(res3)){
//            System.out.println("All result is equals!");
//        }
//        else System.out.println("Results not equals!");

//        SportsmanGenerator.generateSportsman(250000).stream().forEach(System.out::println);
//        CompetitionGenerator.generate(2, 20).stream().forEach(System.out::println);

//        CompetitionPlaceGenerator.getCompetitionPlaces().stream().forEach(System.out::println);
    }
}
