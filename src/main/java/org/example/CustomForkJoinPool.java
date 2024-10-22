package org.example;

import org.example.models.Coach;
import org.example.models.Competition;
import org.example.models.Sportsman;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.RecursiveTask;

public class CustomForkJoinPool extends RecursiveTask<ConcurrentMap<String, List<Coach>>> {

    private final List<Competition> competitionList;
    public CustomForkJoinPool(List<Competition> competitions){
        this.competitionList = competitions;
    }

    @Override
    protected ConcurrentMap<String, List<Coach>> compute() {
        if (competitionList.size() <= 100){
            return competitionList.stream()
                    .collect(java.util.stream.Collectors.toConcurrentMap(
                            competition -> competition.getId().toString(),
                            competition -> competition.getSportsmanList().stream()
                                    .map(Sportsman::getCoach)
                                    .distinct()
                                    .collect(java.util.stream.Collectors.toList())
                    ));
        }
        else {
            var comSize = competitionList.size();
            var middle = comSize / 2;
            CustomForkJoinPool leftTask = new CustomForkJoinPool(competitionList.subList(0, middle));
            CustomForkJoinPool rightTask = new CustomForkJoinPool(competitionList.subList(middle, comSize));

            leftTask.fork();
            rightTask.fork();
            var resLeft = leftTask.join();
            var resRight = rightTask.join();

            ConcurrentMap<String, List<Coach>> res = new ConcurrentHashMap<>(resRight);
            res.putAll(resLeft);
            return res;
        }
    }
}
