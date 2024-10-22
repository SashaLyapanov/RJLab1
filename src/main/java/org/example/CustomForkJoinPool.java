package org.example;

import org.example.models.Coach;
import org.example.models.Competition;
import org.example.models.Sportsman;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class CustomForkJoinPool extends RecursiveTask<ConcurrentHashMap<String, List<Coach>>> {

    private final List<Competition> competitionList;
    public CustomForkJoinPool(List<Competition> competitions){
        this.competitionList = competitions;
    }

    @Override
    protected ConcurrentHashMap<String, List<Coach>> compute() {
        if (competitionList.size() <= 10){
            return competitionList.stream()
                    .collect(Collectors.toMap(
                            competition -> competition.getId().toString(),
                            competition -> competition.getSportsmanList().stream()
                                    .map(Sportsman::getCoach)
                                    .distinct()
                                    .collect(java.util.stream.Collectors.toList()),
                            (key1, key2) -> key1,
                            ConcurrentHashMap::new)
                    );
        }
        else {
            ConcurrentHashMap<String, List<Coach>> res = new ConcurrentHashMap<>();
            var comSize = competitionList.size();
            var middle = comSize / 2;
            CustomForkJoinPool leftTask = new CustomForkJoinPool(competitionList.subList(0, middle));
            CustomForkJoinPool rightTask = new CustomForkJoinPool(competitionList.subList(middle, comSize));
            rightTask.fork();
            res.putAll(leftTask.compute());
            res.putAll(rightTask.join());
            return res;
        }
    }
}
