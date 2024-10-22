package org.example;

import org.example.models.Coach;
import org.example.models.Competition;
import org.example.models.Sportsman;

import java.util.*;

public class Action {
    private List<Competition> competitionList;

    public Map<String, List<Coach>> simpleLoop() {
        Map<String, List<Coach>> finalResult = new HashMap<>();
        var start = System.currentTimeMillis();
        for (Competition competition : competitionList) {
            List<Sportsman> sportsmanList;
            List<Coach> coachList = new ArrayList<>();
            List<Coach> coachListWithoutDuplicates = new ArrayList<>();
            sportsmanList = competition.getSportsmanList();
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
        Map<String, List<Coach>> finalResult;
        var start = System.currentTimeMillis();
        finalResult = competitionList.stream()
                .collect(java.util.stream.Collectors.toMap(
                        competition -> competition.getId().toString(),
                        competition -> competition.getSportsmanList().stream()
                                .map(Sportsman::getCoach)
                                .distinct()
                                .collect(java.util.stream.Collectors.toList())
                ));
        System.out.println("Стримы: " + (System.currentTimeMillis() - start) + "mc");
        return finalResult;
    }

    public Map<String, List<Coach>> customCollectors() {
        Map<String, List<Coach>> finalResult;
        var start = System.currentTimeMillis();
        finalResult = competitionList.stream()
                .collect(new CustomCollector());
        System.out.println("Custom Collector : " + (System.currentTimeMillis() - start) + "ms");
        return finalResult;
    }

    public Action(List<Competition> competitionList) {
        this.competitionList = competitionList;
    }
}
