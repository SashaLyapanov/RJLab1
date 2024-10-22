package org.example;

import org.example.generators.CompetitionGenerator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Action actionSequence = new Action(CompetitionGenerator.generate(60700, 500));
        Action actionParallel = new Action(CompetitionGenerator.generate(60700, 500));
//        Action actionSequence = new Action(CompetitionGenerator.generate(50000, 500));
//        Action actionParallel = new Action(CompetitionGenerator.generate(250000, 500));
//        Map res1 = action.simpleLoop();
        Map res2parallel = actionParallel.streamLoop();
        Map res2posled = actionSequence.streamLoop1();
//        Map res3parallel = actionParallel.customCollectorsParallel();
//        Map res3posled = actionSequence.customCollectorsSequence();

//        if (res1.equals(res2) && res2.equals(res3)){
//        if (res2parallel.equals(res2posled) && res2posled.equals(res3parallel) && res3parallel.equals(res3posled)){
//            System.out.println("All result is equals!");
//        }
//        else System.out.println("Results not equals!");
    }
}
