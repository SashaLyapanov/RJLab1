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

public class CustomSubscriber extends ResourceSubscriber<Competition> {
    private static final int DATA_PACKAGE = 10;
    private int currentElementNumber = 0;

    @Override
    protected void onStart() {
        request(DATA_PACKAGE);
    }

    @Override
    public void onNext(@NonNull Competition competitions) {
        currentElementNumber++;

        var tmpId = competitions.getId().toString();
        var tmpCoach = competitions.getSportsmanList()
                .stream()
                .map(Sportsman::getCoach).distinct().collect(Collectors.toCollection(ArrayList::new));

        System.out.println("Current element: " + currentElementNumber);

        if(currentElementNumber % DATA_PACKAGE == 0){
            System.out.println("Request data");
            request(DATA_PACKAGE);
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {
        System.out.println("Completed!!");
    }
}
