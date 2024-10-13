package org.example;

import org.example.generators.CompetitionGenerator;
import org.example.generators.CompetitionPlaceGenerator;
import org.example.generators.SportsmanGenerator;
import org.example.models.Competition;
import org.example.models.CompetitionPlace;
import org.example.models.CompetitionTypes;
import org.example.models.Sportsman;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Action action = new Action(CompetitionGenerator.generate(250000, 500));
        var res1 = action.simpleLoop();
        var res2 = action.streamLoop();
        var res3 = action.customCollectors();
        if (res1.equals(res2) && res2.equals(res3)){
            System.out.println("All result is equals!");
        }
        else System.out.println("Results not equals!");
    }
}
