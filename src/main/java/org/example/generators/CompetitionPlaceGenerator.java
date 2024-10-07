package org.example.generators;

import org.example.models.CompetitionPlace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CompetitionPlaceGenerator {

    static List<String> cities = Arrays.asList("Москва", "Санкт-Петербург", "Екатеринбург", "Новосибирск", "Казань", "Нижний Новгород", "Челябинск", "Самара", "Ростов-на-Дону", "Уфа", "Красноярск", "Волгоград", "Пермь", "Тюмень", "Иркутск", "Воронеж", "Саратов", "Барнаул", "Тольятти", "Ярославль");

    public static CompetitionPlace getCompetitionPlaces() {
        Random random = new Random();
        int indexCity = random.nextInt(cities.size());
        float shirota = random.nextFloat(41, 81);
        float dolgota = random.nextFloat(19, 169);
        return new CompetitionPlace("Россия", cities.get(indexCity), shirota + ", " + dolgota);
    }


}
