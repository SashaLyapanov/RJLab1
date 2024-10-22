package org.example;

import org.example.generators.CompetitionGenerator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        Action actionSequence = new Action(CompetitionGenerator.generate(35000, 500));
//        Action actionParallel = new Action(CompetitionGenerator.generate(40000, 500));
        Action actionFork = new Action(CompetitionGenerator.generate(250000, 500));
//        Action actionParallel = new Action(CompetitionGenerator.generate(250000, 500));
//        Map res1 = action.simpleLoop();
//        Map res2parallel = actionParallel.streamLoopParallel();
//        Map res2posled = actionSequence.streamLoopSequence();
//        Map res3parallel = actionParallel.customCollectorsParallel();
//        Map res3posled = actionSequence.customCollectorsSequence();
        Map forkPool = actionFork.forkJoinPoolStreamLoop();

//        if (res2parallel.equals(res2posled) && res2.equals(res3)){
//        if (res2parallel.equals(res2posled) && res2posled.equals(res3parallel) && res3parallel.equals(res3posled)){
//            System.out.println("All result is equals!");
//        }
//        else System.out.println("Results not equals!");
    }
}
