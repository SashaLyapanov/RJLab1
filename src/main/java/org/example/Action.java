package org.example;

import org.example.models.Coach;
import org.example.models.Competition;
import org.example.models.Sportsman;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Action {
    private List<Competition> competitionList;

    private long delay = 10;

    public Map<String, List<Coach>> simpleLoop() {
        Map<String, List<Coach>> finalResult = new HashMap<>();
        var start = System.currentTimeMillis();
        for (Competition competition : competitionList) {
            List<Sportsman> sportsmanList;
            List<Coach> coachList = new ArrayList<>();
            List<Coach> coachListWithoutDuplicates = new ArrayList<>();

//            Вариант без задержки
//            sportsmanList = competition.getSportsmanList();

//            Вариант с задержкой
            sportsmanList = competition.getSportsmanList(delay);

            for (Sportsman sportsman : sportsmanList) {
                coachList.add(sportsman.getCoach());
            }
            for (Coach coach : coachList) {
                if (!coachListWithoutDuplicates.contains(coach)) {
                    coachListWithoutDuplicates.add(coach);
                }
            }
            finalResult.put(competition.getId().toString(), coachListWithoutDuplicates);
        }
        System.out.println("Итерационный цикл: " + (System.currentTimeMillis() - start) + "mc");
        return finalResult;
    }

    public Map<String, List<Coach>> streamLoop() {
        Map<String, List<Coach>> finalResult = new ConcurrentHashMap<>();
        var start = System.currentTimeMillis();
        competitionList.parallelStream()
                .forEach(competition -> {
                    String key = competition.getId().toString();
                    List<Coach> coaches = competition.getSportsmanList(delay).parallelStream()
                            .map(Sportsman::getCoach)
                            .distinct()
                            .collect(Collectors.toList());
                    finalResult.put(key, coaches);
                });
        System.out.println("Стримы: " + (System.currentTimeMillis() - start) + "mc");
        return finalResult;
    }

    public Map<String, List<Coach>> customCollectors() {
        Map<String, List<Coach>> finalResult;
        var start = System.currentTimeMillis();
        finalResult = competitionList.parallelStream()
                .collect(new CustomCollector(delay));
        System.out.println("Custom Collector : " + (System.currentTimeMillis() - start) + "ms");
        return finalResult;
    }

    public Action(List<Competition> competitionList) {
        this.competitionList = competitionList;
    }
}
