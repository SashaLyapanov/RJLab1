package org.example;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.example.generators.CompetitionGenerator;
import org.example.generators.CompetitionPlaceGenerator;
import org.example.generators.SportsmanGenerator;
import org.example.models.Competition;
import org.example.models.CompetitionTypes;
import org.example.models.Sportsman;
import org.example.rx.CustomSubscriber;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.Flow;

public class Main {
    public static void main(String[] args) throws RunnerException {
//        Options options = new OptionsBuilder()
//                .include(StartWork.class.getSimpleName())
//                .forks(1)
//                .build();
//
//        new Runner(options).run();







//        int Count = 1000;
//        int sportCount = 400;
//        List<Sportsman> sportsmanList = SportsmanGenerator.generateSportsman(sportCount);
//        Random random = new Random();
//        @NonNull Flowable<Object> competitionFlow = Flowable.create(emitter -> {
//            for(int i = 0; i < Count; i++){
//                int spCount = random.nextInt(15, sportCount);
//                Collections.shuffle(sportsmanList);
//                emitter.onNext(
//                       new Competition(UUID.randomUUID(), random.nextInt(2000),
//                               LocalDate.now().plusMonths(random.nextInt(1,3)),
//                               CompetitionTypes.values()[random.nextInt(2)],
//                               sportsmanList.subList(0, spCount),
//                               CompetitionPlaceGenerator.getCompetitionPlaces()));
//            }
//        }, BackpressureStrategy.BUFFER)
//                .subscribeOn(Schedulers.io());
//
//        competitionFlow.observeOn(Schedulers.computation())
//                .subscribe((Consumer<? super Object>) new CustomSubscriber());

        Action Test1 = new Action(CompetitionGenerator.generate(500, 500));
        Action Test2 = new Action(CompetitionGenerator.generate(2000, 500));

        Test2.streamLoopParallel();
        Test2.forkJoinPoolStreamLoop();
        Test2.rxReleaseMet();
//        Test2.streamLoopSequenceRx();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


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
