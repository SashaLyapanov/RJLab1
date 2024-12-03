package org.example.generators;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import org.example.models.Competition;
import org.example.models.CompetitionTypes;
import org.example.models.Sportsman;

import java.time.LocalDate;
import java.util.*;

public class CompetitionGenerator {


    public static List<Competition> generate(int countCompetition, int countSportsman) {
        List<Competition> competitionList = new ArrayList<>();
        List<Sportsman> sportsmanList = SportsmanGenerator.generateSportsman(countSportsman);
        Random random = new Random();
        for (int i = 0; i < countCompetition; i++) {
            int spCount = random.nextInt(15, countSportsman);
            Collections.shuffle(sportsmanList);
            competitionList.add(new Competition(UUID.randomUUID(), random.nextInt(2000), LocalDate.now().plusMonths(random.nextInt(1,3)),
                    CompetitionTypes.values()[random.nextInt(2)], sportsmanList.subList(0, spCount), CompetitionPlaceGenerator.getCompetitionPlaces()));

            //System.out.println("Iteration :" + i);
        }
        return competitionList;
    }

    public static Flowable<List<Competition>> generateFlowable(int countCompetition, int countSportsman) {
        Random random = new Random();
        return Flowable.create(emitter -> {
            var sportsmanFlow = SportsmanGenerator.generateSportsmanFlowable(countSportsman);
            for (int i = 0; i < countCompetition; i++) {
                int spCount = random.nextInt(15, countSportsman);
            }
        }, BackpressureStrategy.BUFFER);
    }
}

