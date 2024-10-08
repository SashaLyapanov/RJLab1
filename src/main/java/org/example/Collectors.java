package org.example;

import org.example.models.Competition;

import org.javatuples.Pair;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class Collectors
        implements Collector<Competition, Map<String, List<String>>, Map<String, List<String>>> {

    public static Collectors toCollectorMap(){
        return new Collectors();
    }

    @Override
    public Supplier<Map<String, List<String>>> supplier() {
        return HashMap<String, List<String>>::new;
    }

    @Override
    public BiConsumer<Map<String, List<String>>, Competition> accumulator() {
        return (list, competition) -> list.put(competition.getId().toString(), competition.getResult().getWinners());
    }

    @Override
    public BinaryOperator<Map<String, List<String>>> combiner() {
        return (mainList, newList) -> {
            mainList.putAll(newList);
            return mainList;
        };
    }

    @Override
    public Function<Map<String, List<String>>, Map<String, List<String>>> finisher() {
        return Collections::unmodifiableMap;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.UNORDERED);
    }
}
