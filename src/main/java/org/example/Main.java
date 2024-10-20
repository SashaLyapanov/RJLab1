package org.example;

import org.example.generators.CompetitionGenerator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        Action action = new Action(CompetitionGenerator.generate(5000, 500));
//        Action action = new Action(CompetitionGenerator.generate(50000, 500));
        Action action = new Action(CompetitionGenerator.generate(250000, 500));
        Map res1 = action.simpleLoop();
        Map res2 = action.streamLoop();
        Map res3 = action.customCollectors();

        if (res1.equals(res2) && res2.equals(res3)){
            System.out.println("All result is equals!");
        }
        else System.out.println("Results not equals!");
    }
}
