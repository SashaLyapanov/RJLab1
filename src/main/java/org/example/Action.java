package org.example;

import org.example.models.Coach;
import org.example.models.Competition;
import org.example.models.Sportsman;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class Action {
    private List<Competition> competitionList;

    private long delay = -1; //10;

//    public Map<String, List<Coach>> simpleLoop() {
//        Map<String, List<Coach>> finalResult = new HashMap<>();
//        var start = System.currentTimeMillis();
//        for (Competition competition : competitionList) {
//            List<Sportsman> sportsmanList;
//            List<Coach> coachList = new ArrayList<>();
//            List<Coach> coachListWithoutDuplicates = new ArrayList<>();
//            sportsmanList = competition.getSportsmanList(delay);
//
//            for (Sportsman sportsman : sportsmanList) {
//                coachList.add(sportsman.getCoach());
//            }
//            for (Coach coach : coachList) {
//                if (!coachListWithoutDuplicates.contains(coach)) {
//                    coachListWithoutDuplicates.add(coach);
//                }
//            }
//            finalResult.put(competition.getId().toString(), coachListWithoutDuplicates);
//        }
//        System.out.println("Итерационный цикл: " + (System.currentTimeMillis() - start) + "mc");
//        return finalResult;
//    }

    //параллельно
    public Map<String, List<Coach>> streamLoopParallel() {
        Map<String, List<Coach>> finalResult = new ConcurrentHashMap<>();
        var start = System.currentTimeMillis();
        competitionList.parallelStream()
                .forEach(competition -> {
                    String key = competition.getId().toString();
//                    List<Coach> coaches = competition.getSportsmanList(delay).parallelStream()
                    List<Coach> coaches = competition.getSportsmanList().parallelStream()
                            .map(Sportsman::getCoach)
                            .distinct()
                            .collect(Collectors.toList());
                    finalResult.put(key, coaches);
                });
        System.out.println("Стримы параллельно: " + (System.currentTimeMillis() - start) + "mc");
        return finalResult;
    }

    //последовательно
    public Map<String, List<Coach>> streamLoopSequence() {
        Map<String, List<Coach>> finalResult = new ConcurrentHashMap<>();
        var start = System.currentTimeMillis();
        competitionList.stream()
                    .forEach(competition -> {
                        String key = competition.getId().toString();
//                        List<Coach> coaches = competition.getSportsmanList(delay).stream()
                        List<Coach> coaches = competition.getSportsmanList().stream()
                                .map(Sportsman::getCoach)
                                .distinct()
                                .collect(Collectors.toList());
                        finalResult.put(key, coaches);
                    });
        System.out.println("Стримы последовательно: " + (System.currentTimeMillis() - start) + "mc");
        return finalResult;
    }

//    //Реализация streamLoop() через ForkJoinPool
    public Map<String, List<Coach>> forkJoinPoolStreamLoop() {
        Map<String, List<Coach>> finalResult;
        ForkJoinPool forkJoinPool = new ForkJoinPool(8);
        var start = System.currentTimeMillis();

        finalResult = forkJoinPool.invoke(new CustomForkJoinPool(competitionList));

        System.out.println("ForkJoinPool: " + (System.currentTimeMillis() - start) + "mc");
        return finalResult;
    }

    //параллельно
    public Map<String, List<Coach>> customCollectorsParallel() {
        Map<String, List<Coach>> finalResult;
        var start = System.currentTimeMillis();
        finalResult = competitionList.parallelStream()
                .collect(new CustomCollector(delay));
//                .collect(new CustomCollector());
        System.out.println("Custom Collector параллельно: " + (System.currentTimeMillis() - start) + "ms");
        return finalResult;
    }

    //последовательно
    public Map<String, List<Coach>> customCollectorsSequence() {
        Map<String, List<Coach>> finalResult;
        var start = System.currentTimeMillis();
        finalResult = competitionList.stream()
                .collect(new CustomCollector(delay));
//                .collect(new CustomCollector());
        System.out.println("Custom Collector последовательно: " + (System.currentTimeMillis() - start) + "ms");
        return finalResult;
    }

    public Action(List<Competition> competitionList) {
        this.competitionList = competitionList;
    }
}
