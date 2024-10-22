package org.example;

import org.example.models.Coach;
import org.example.models.Competition;
import org.example.models.Sportsman;

import java.util.ArrayList;
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
        if (competitionList.size() <= 20000){
            return competitionList.parallelStream()
                    .collect(Collectors.toMap(
                            competition -> competition.getId().toString(),
                            competition -> new ArrayList<>(competition.getSportsmanList()
                                    .stream()
                                    .map(Sportsman::getCoach)
                                    .collect(Collectors.toSet())),
                            (key1, key2) -> key1,
                            ConcurrentHashMap::new)
                    );
        }
        else {
            var comSize = competitionList.size();
            var middle = comSize / 2;
            CustomForkJoinPool leftTask = new CustomForkJoinPool(competitionList.subList(0, middle));
            CustomForkJoinPool rightTask = new CustomForkJoinPool(competitionList.subList(middle, comSize));
            rightTask.fork();
            ConcurrentHashMap<String, List<Coach>> left = leftTask.compute();
            left.putAll(rightTask.join());
            return left;
        }
    }
}
