package org.example;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.example.models.Coach;
import org.example.models.Competition;
import org.example.models.Sportsman;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import org.example.rx.CustomSubscriber;


public class Action {
    private final List<Competition> competitionList;

    private final long delay = 3; //10;
    
    // rx
    public Map<String, List<Coach>> rxReleaseMet(){
        var start = System.currentTimeMillis();
        Map<String, List<Coach>> res = new ConcurrentHashMap<>();
        Observable<List<Competition>> competitionObservable = Observable.just(competitionList)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation());
        competitionObservable
                .flatMap(Observable::fromIterable)
                .map(competition -> new AbstractMap.SimpleEntry<>(
                        competition.getId().toString(),
                        competition.getSportsmanList().stream()
                                .map(Sportsman::getCoach)
                                .distinct()
                                .collect(Collectors.toList())
                ))
                .toList()
                .subscribe(
                        value -> {
                            System.out.println("Реактивный метод: " + (System.currentTimeMillis() - start) + "mc, Size: " + value.size());
                        },
                        Throwable::printStackTrace
                );
//        System.out.println("Реактивный метод: " + (System.currentTimeMillis() - start) + "mc");
        return res;
    }

    public void streamLoopSequenceRx() {
        var start = System.currentTimeMillis();

        Observable.fromIterable(competitionList)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(competition ->
                        new AbstractMap.SimpleEntry<>(
                                competition.getId().toString(),
                                new ArrayList<>(competition.getSportsmanList(delay)
                                        .stream()
                                        .map(Sportsman::getCoach)
                                        .collect(Collectors.toSet())
                                )
                        )
                )
                .collect(
                        Collectors.toMap(
                            AbstractMap.SimpleEntry::getKey,
                            AbstractMap.SimpleEntry::getValue,
                            (key1, key2) -> key1, // выбор значения при конфликте ключей
                            ConcurrentHashMap::new
                        )
                )
                .blockingSubscribe(
                        res -> {
                            // Здесь переменная res будет ConcurrentHashMap, аналогичный результат стрима
                            System.out.println("Результат собран в ConcurrentHashMap");
                            System.out.println("Реактивный метод: " + (System.currentTimeMillis() - start) + "mc");
                        },
                        throwable -> {
                            // Обработка ошибок, если нужно
                            throwable.printStackTrace();
                        }
                );
    }
    
    //параллельно
    public void streamLoopParallel() {
        var start = System.currentTimeMillis();
        var res = competitionList.parallelStream()
                .collect(Collectors.toMap(
                        competition -> competition.getId().toString(),
                        competition -> new ArrayList<>(competition.getSportsmanList()
                                .stream()
                                .map(Sportsman::getCoach)
                                .collect(Collectors.toSet())),
                        (key1, key2) -> key1,
                        ConcurrentHashMap::new)
                );
        System.out.println("Реактивный метод: " + (System.currentTimeMillis() - start) + "mc");
    }

    //последовательно
    public Map<String, List<Coach>> streamLoopSequence() {
//        var start = System.currentTimeMillis();
        return competitionList.stream()
                .collect(Collectors.toMap(
                        competition -> competition.getId().toString(),
                        competition -> new ArrayList<>(competition.getSportsmanList()
                                .stream()
                                .map(Sportsman::getCoach)
                                .collect(Collectors.toSet())),
                        (key1, key2) -> key1,
                        ConcurrentHashMap::new)
                );
//        System.out.println("Стримы последовательно: " + (System.currentTimeMillis() - start) + "mc");
    }

//    //Реализация streamLoop() через ForkJoinPool

    public Map<String, List<Coach>> forkJoinPoolStreamLoop() {
        Map<String, List<Coach>> finalResult;
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        var start = System.currentTimeMillis();

        finalResult = forkJoinPool.invoke(new CustomForkJoinPool(competitionList));

        System.out.println("ForkJoinPool: " + (System.currentTimeMillis() - start) + "mc");
        return finalResult;
    }

    //параллельно
    public Map<String, List<Coach>> customCollectorsParallel() {
        Map<String, List<Coach>> finalResult;
        var start = System.currentTimeMillis();
        finalResult = competitionList.parallelStream()
                .collect(new CustomCollector(delay));
//                .collect(new CustomCollector());
        System.out.println("Custom Collector параллельно: " + (System.currentTimeMillis() - start) + "ms");
        return finalResult;
    }

    //последовательно
    public Map<String, List<Coach>> customCollectorsSequence() {
        Map<String, List<Coach>> finalResult;
        var start = System.currentTimeMillis();
        finalResult = competitionList.stream()
                .collect(new CustomCollector(delay));
//                .collect(new CustomCollector());
        System.out.println("Custom Collector последовательно: " + (System.currentTimeMillis() - start) + "ms");
        return finalResult;
    }

    public Action(List<Competition> competitionList) {
        this.competitionList = competitionList;
    }
}
