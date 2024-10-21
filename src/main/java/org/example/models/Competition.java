package org.example.models;

import lombok.Data;
import org.example.generators.SportsmanGenerator;

import java.time.LocalDate;
import java.util.*;

@Data
public class Competition {
    private UUID id;

    private int startPay;

    private LocalDate date;

    private CompetitionTypes competitionTypes;

    private List<Sportsman> sportsmanList;

    public record CompetitionResult(String organizatorFIO,
                                     String numberOrg,
                                     String mailOrg,
                                     Map<Integer, Sportsman> sportsmanPlace) {}

    private CompetitionResult result;

    private CompetitionPlace place;

    public Competition(UUID id, int startPay, LocalDate date, CompetitionTypes competitionTypes, List<Sportsman> sportsmanList, CompetitionPlace place) {
        this.id = id;
        this.startPay = startPay;
        this.date = date;
        this.competitionTypes = competitionTypes;
        this.sportsmanList = sportsmanList;
        this.place = place;
        this.result = generateResult(sportsmanList);
    }

    public List<Sportsman> getSportsmanList(long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return this.sportsmanList;
    }

    private CompetitionResult generateResult(List<Sportsman> sportsmanList) {
        Random random = new Random();
        String fio = SportsmanGenerator.generateFIO();
        String mail = fio.substring(0, fio.indexOf(" ")) + random.nextInt(1, 1000) + "@yandex.ru";
        Map<Integer, Sportsman> sportsmanPlace = generatePlaces(sportsmanList);

        CompetitionResult competitionResult = new CompetitionResult(fio, "8960719" + random.nextInt(10, 100) + random.nextInt(10, 100),
                mail, sportsmanPlace);

        return competitionResult;
    }

    private Map<Integer, Sportsman> generatePlaces(List<Sportsman> sportsmanList) {
        List<Sportsman> sportsmanList1 = new ArrayList<>(sportsmanList);
        Map<Integer, Sportsman> sportsmanMap = new HashMap<>();
        Random random = new Random();
        for (int i = 0; i < sportsmanList.size(); i++) {
            int indexInList = random.nextInt(sportsmanList1.size());
            sportsmanMap.put(i+1, sportsmanList1.get(indexInList));
            sportsmanList1.remove(indexInList);
        }
        return sportsmanMap;
    }
}
