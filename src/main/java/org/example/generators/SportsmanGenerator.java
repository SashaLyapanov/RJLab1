package org.example.generators;

import org.example.models.Coach;
import org.example.models.Sportsman;

import java.time.LocalDate;
import java.util.*;

public class SportsmanGenerator {
    static private List<String> surname = Arrays.asList("Барсуков", "Калачев", "Григорьев", "Баранов",
            "Макаров", "Соколов", "Колесников", "Головин", "Овсянников", "Воронов", "Захаров",
            "Никифоров", "Миронов", "Любимов", "Назаров", "Воронков", "Евдокимов", "Шишкин", "Корнеев", "Краснов",
            "Иванов");

    static private List<String> NS = Arrays.asList("А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ", "Э", "Ю", "Я");

    static Random random = new Random();

    private static List<Coach> coaches = CoachGenerator.generateCoach(15);

    public static List<Sportsman> generateSportsman(int count) {
        List<Sportsman> sportsmanList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int indexSurname = random.nextInt(surname.size());
            String fio = generateFIO();
            int age = random.nextInt(15, 55);
            LocalDate birth = LocalDate.now().minusYears(age);
            sportsmanList.add(new Sportsman(UUID.randomUUID(), fio, age, birth, surname.get(indexSurname) + i + "@mail.ru", coaches.get(random.nextInt(coaches.size()))));
        }
        return sportsmanList;
    }

    public static String generateFIO() {
        int indexSurname = random.nextInt(surname.size());
        int indexName = random.nextInt(NS.size());
        int indexPatronymic = random.nextInt(NS.size());
        return surname.get(indexSurname) + " " + NS.get(indexName) + ". " + NS.get(indexPatronymic) + ".";
    }
}