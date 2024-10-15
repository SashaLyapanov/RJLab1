package org.example;

import org.example.models.Coach;
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

public class CustomCollector implements Collector<Competition, Map<String, List<Coach>>, Map<String, List<Coach>>> {

    @Override
    public Supplier<Map<String, List<Coach>>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<String, List<Coach>>, Competition> accumulator() {
        return (map, competition) -> {
            map.put(competition.getId().toString(), competition.getSportsmanList().stream()
                    .map(Sportsman::getCoach)
                    .distinct()
                    .toList());
        };
    }

    @Override
    public BinaryOperator<Map<String, List<Coach>>> combiner() {
        return (map1, map2) -> {
            map1.putAll(map2);
            return map1;
        };
    }

    @Override
    public Function<Map<String, List<Coach>>, Map<String, List<Coach>>> finisher() {
        return (map) -> {
            return map;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.UNORDERED);
    }
}
