package org.example;

import org.example.generators.CompetitionGenerator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Action action = new Action(CompetitionGenerator.generate(5000, 500));
        Action action1 = new Action(CompetitionGenerator.generate(26800, 500));
//        Action action = new Action(CompetitionGenerator.generate(50000, 500));
//        Action action = new Action(CompetitionGenerator.generate(250000, 500));
//        Map res1 = action.simpleLoop();
        Map res2parallel = action1.streamLoop();
        Map res2posled = action.streamLoop1();
        Map res3parallel = action1.customCollectors();
        Map res3posled = action.customCollectors1();

//        if (res1.equals(res2) && res2.equals(res3)){
        if (res2parallel.equals(res2posled) && res2posled.equals(res3parallel) && res3parallel.equals(res3posled)){
            System.out.println("All result is equals!");
        }
        else System.out.println("Results not equals!");
    }
}
