package org.example;

import org.example.models.Competition;
import org.example.Collectors;
import org.example.models.Sportsman;
import org.javatuples.Pair;

import java.util.*;

public class Action {
    private List<Competition> competitionList;

    public Map<String, List<Sportsman>> simpleLoop(){
        Map<String, List<Sportsman>>finalResult = new HashMap<>();
        var start = System.currentTimeMillis();
        for (var item: competitionList){
            List<Sportsman> tmpSportsmen = new ArrayList<>();
            tmpSportsmen.add(item.getResult().sportsmanPlace().get(1));
            tmpSportsmen.add(item.getResult().sportsmanPlace().get(2));
            tmpSportsmen.add(item.getResult().sportsmanPlace().get(3));
           finalResult.put(item.getId().toString(), tmpSportsmen);
        }
        System.out.println("Итерационный цикл: " + (System.currentTimeMillis() - start) + "mc" );
        return finalResult;
    }

    public Map<String, List<Sportsman>> streamLoop() {
        Map<String, List<Sportsman>> finalResult = new HashMap<>();
        var start = System.currentTimeMillis();
        competitionList.stream()
                .map(item -> finalResult.put(item.getId().toString(), item.getResult()
                                        .sportsmanPlace()
                                        .entrySet()
                                        .stream()
                                        .filter(key -> key.getKey() <= 3)
                                        .map(Map.Entry::getValue)
                                        .toList()
                        )
                );
        System.out.println("Stream : " + (System.currentTimeMillis() - start) + "mc" );
        return finalResult;
    }

    public  Map<String, List<Sportsman>> customCollectors(){
        var start = System.currentTimeMillis();
        Map<String, List<Sportsman>> finalResult = competitionList.stream()
                .collect(Collectors.toCollectorMap());
        System.out.println("Custom Collector : " + (System.currentTimeMillis() - start) + "ms" );
        return finalResult;
    }

    public Action(List<Competition> com){
        competitionList = com;
    }
}
