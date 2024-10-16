package org.example.generators;

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
}

