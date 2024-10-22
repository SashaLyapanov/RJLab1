package org.example;

import org.example.generators.CompetitionGenerator;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(StartWork.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(options).run();

//        Action actionFork = new Action(CompetitionGenerator.generate(250000, 500));
//        Action actionParallel = new Action(CompetitionGenerator.generate(250000, 500));
//        Map res1 = action.simpleLoop();
//
//        Map res3parallel = actionParallel.customCollectorsParallel();
//        Map res3posled = actionSequence.customCollectorsSequence();
//        Map forkPool = actionFork.forkJoinPoolStreamLoop();

//        if (res2parallel.equals(res2posled) && res2.equals(res3)){
//        if (res2parallel.equals(res2posled) && res2posled.equals(res3parallel) && res3parallel.equals(res3posled)){
//            System.out.println("All result is equals!");
//        }
//        else System.out.println("Results not equals!");
    }
}
