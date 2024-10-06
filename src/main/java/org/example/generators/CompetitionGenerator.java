package org.example.generators;

import org.example.models.Competition;
import org.example.models.CompetitionTypes;
import org.example.models.Sportsman;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CompetitionGenerator {


    public static List<Competition> generate(int countCompetition, int countSportsman) {
        List<Competition> competitionList = new ArrayList<Competition>();
        Random random = new Random();
        for (int i = 0; i < countCompetition; i++) {
            List<Sportsman> sportsmanList = SportsmanGenerator.generateSportsman(countSportsman);

            competitionList.add(new Competition(UUID.randomUUID(), random.nextInt(2000), LocalDate.now().plusMonths(random.nextInt(1,3)),
                    CompetitionTypes.values()[random.nextInt(2)], sportsmanList, CompetitoinPlaceGenerator.getCompetitionPlaces()));

        }
        return competitionList;
    }
}

