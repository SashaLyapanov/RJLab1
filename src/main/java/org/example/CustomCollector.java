package org.example;

import org.example.models.Competition;
import org.example.models.Sportsman;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CustomCollector implements Collector<Competition, Map<String, List<Sportsman>>, Map<String, List<Sportsman>>> {


    @Override
    public Supplier<Map<String, List<Sportsman>>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<String, List<Sportsman>>, Competition> accumulator() {
        return null;
    }

    @Override
    public BinaryOperator<Map<String, List<Sportsman>>> combiner() {
        return null;
    }

    @Override
    public Function<Map<String, List<Sportsman>>, Map<String, List<Sportsman>>> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of();
    }
}
