package org.example.generators;

import org.example.models.Coach;

import java.time.LocalDate;
import java.util.*;

public class CoachGenerator {

    static private List<String> surname = Arrays.asList("Барсуков", "Калачев", "Григорьев", "Баранов",
            "Макаров", "Соколов", "Колесников", "Головин", "Овсянников", "Воронов", "Захаров",
            "Никифоров", "Миронов", "Любимов", "Назаров", "Воронков", "Евдокимов", "Шишкин", "Корнеев", "Краснов",
            "Иванов");

    static private List<String> NS = Arrays.asList("А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ", "Э", "Ю", "Я");

    static Random random = new Random();

    public static List<Coach> generateCoach(int count) {
        List<Coach> coacheList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int indexSurname = random.nextInt(surname.size());
            String fio = generateFIO();
            int age = random.nextInt(15, 55);
            LocalDate birth = LocalDate.now().minusYears(age);
            coacheList.add(new Coach(UUID.randomUUID(), fio, age, birth, surname.get(indexSurname) + i + "@mail.ru"));
        }
        return coacheList;
    }

    public static String generateFIO() {
        int indexSurname = random.nextInt(surname.size());
        int indexName = random.nextInt(NS.size());
        int indexPatronymic = random.nextInt(NS.size());
        return surname.get(indexSurname) + " " + NS.get(indexName) + ". " + NS.get(indexPatronymic) + ".";
    }

}
