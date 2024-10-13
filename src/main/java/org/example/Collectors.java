package org.example;

import org.example.models.Competition;

import org.example.models.Sportsman;
import org.javatuples.Pair;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class Collectors
        implements Collector<Competition, Map<String, List<Sportsman>>, Map<String, List<Sportsman>>> {

    public static Collectors toCollectorMap(){
        return new Collectors();
    }

    private List<Sportsman> getWinners(Competition competition){
        return competition.getResult().sportsmanPlace()
                .entrySet()
                .stream()
                .filter(item -> item.getKey() <= 3)
                .map(Map.Entry::getValue)
                .toList();
    }

    @Override
    public Supplier<Map<String, List<Sportsman>>> supplier() {
        return HashMap<String, List<Sportsman>>::new;
    }

    @Override
    public BiConsumer<Map<String, List<Sportsman>>, Competition> accumulator() {
        return (list, competition) -> list.put(competition.getId().toString(),
                getWinners(competition));
    }

    @Override
    public BinaryOperator<Map<String, List<Sportsman>>> combiner() {
        return (mainList, newList) -> {
            mainList.putAll(newList);
            return mainList;
        };
    }

    @Override
    public Function<Map<String, List<Sportsman>>, Map<String, List<Sportsman>>> finisher() {
        return Collections::unmodifiableMap;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.UNORDERED);
    }
}
