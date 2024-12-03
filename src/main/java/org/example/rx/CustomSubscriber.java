package org.example.rx;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subscribers.ResourceSubscriber;
import org.example.models.Competition;
import org.example.models.Sportsman;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class CustomSubscriber extends ResourceSubscriber<List<Competition>> {
    private static final int DATA_PACKAGE = 10;
    private int currentElementNumber = 0;

    @Override
    protected void onStart() {
        request(DATA_PACKAGE);
    }

    @Override
    public void onNext(@NonNull List<Competition> competitions) {
        currentElementNumber++;

        competitions.parallelStream()
                .collect(Collectors.toMap(
                        competition -> competition.getId().toString(),
                        competition -> new ArrayList<>(competition.getSportsmanList()
                                .stream()
                                .map(Sportsman::getCoach)
                                .collect(Collectors.toSet())),
                        (key1, key2) -> key1,
                        ConcurrentHashMap::new)
                );

        if(currentElementNumber % DATA_PACKAGE == 0){
            request(DATA_PACKAGE);
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
