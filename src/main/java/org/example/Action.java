package org.example;

import org.example.models.Competition;
import org.example.Collectors;
import org.example.models.Sportsman;
import org.javatuples.Pair;

import java.util.*;

public class Action {
    private List<Competition> competitionList;

    public Map<String, List<String>> simpleLoop(){
        Map<String, List<String>>finalResult = new HashMap<>();
        var start = System.currentTimeMillis();
        for (var item: competitionList){
//           finalResult.put(item.getId().toString(), item.getResult().getWinners());
        }
        System.out.println("Итерационный цикл: " + (System.currentTimeMillis() - start) + "mc" );
        return finalResult;
    }

    public Map<String, List<Sportsman>> streamLoop() {
//        Map<String, List<String>> finalResult = new HashMap<>();
//        var start = System.currentTimeMillis();
//        competitionList.stream()
//                .forEach(item -> finalResult.put(item.getId().toString(), item.getResult().getWinners()));
//        System.out.println("Stream : " + (System.currentTimeMillis() - start) + "mc" );
//        return finalResult;

        List<Competition> competitions = competitionList;
        Map<String, List<Sportsman>> winners = new HashMap<>();
        competitions.stream()
                .collect(java.util.stream.Collectors.toMap(
                        competition -> competition.getId().toString(),
                        competition -> competition.getResult().sportsmanPlace().keySet().stream()
                                .sorted(Comparator.comparingInt(e -> e))
                                .collect(java.util.stream.Collectors.toList()),
                        (existing, erplacement) -> existing,
                        HashMap::new
                ));
        return winners;
    }

    public  Map<String, List<String>> customCollectors(){
        var start = System.currentTimeMillis();
        Map<String, List<String>> finalResult = competitionList.stream()
                .collect(Collectors.toCollectorMap());
        System.out.println("Custom Collector : " + (System.currentTimeMillis() - start) + "ms" );
        return finalResult;
    }

    public Action(List<Competition> com){
        competitionList = com;
    }
}
