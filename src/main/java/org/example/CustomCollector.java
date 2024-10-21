package org.example;

import org.example.models.Coach;
import org.example.models.Competition;
import org.example.models.Sportsman;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class CustomCollector implements Collector<Competition, Map<String, List<Coach>>, Map<String, List<Coach>>> {

    private long delay;

    public CustomCollector(long delay) {
        this.delay = delay;
    }
    public CustomCollector() {
    }

    @Override
    public Supplier<Map<String, List<Coach>>> supplier() {
        return ConcurrentHashMap::new;
    }

    @Override
    public BiConsumer<Map<String, List<Coach>>, Competition> accumulator() {
        return (map, competition) -> {
            String key = competition.getId().toString();
            List<Coach> coaches = competition.getSportsmanList(delay).stream()
                    .map(Sportsman::getCoach)
                    .distinct()
                    .toList();
            map.merge(key, coaches, (existingList, newList) -> {
                existingList.addAll(newList);
                return existingList;
            });
        };
    }

    @Override
    public BinaryOperator<Map<String, List<Coach>>> combiner() {
        return (map1, map2) -> {
            map2.forEach((key, value) -> map1.merge(key, value, (existingList, newList) -> {
                existingList.addAll(newList);
                return existingList;
            }));
            return map1;
        };
    }

    @Override
    public Function<Map<String, List<Coach>>, Map<String, List<Coach>>> finisher() {
        return map -> map;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.UNORDERED);
    }
}
