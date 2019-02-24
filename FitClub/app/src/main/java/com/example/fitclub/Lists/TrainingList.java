package com.example.fitclub.Lists;

import com.example.fitclub.Models.CommercialTraining;
import com.example.fitclub.Models.Training;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class TrainingList {

    /**
     * An array of sample (Models) items.
     */
    public static final List<Training> ITEMS = new ArrayList<>();

    /**
     * A map of sample (Models) items, by ID.
     */
    public static final Map<String, Training> ITEM_MAP = new HashMap<>();

    private static final int COUNT = 25;

    static {


        Training item1 = new Training.TrainingBuilder().Name("Hatha Yoga").
                StartTime(new Date(2019, 2, 8, 7, 30)).
                EndTime(new Date(2019, 2, 8, 8, 30)).
                GymName("Большой зал").
                ProgramType("Mind&Body (Мягкий фитнес)").
                LevelName("Низкая интенсивность").
                CoachName("Галина").
                CoachFamily("Елизарова").
                Description("Занятие, на котором помимо асан и пранаямы делается акцент на " +
                        "концентрацию внимания и медитацию. Урок рекомендован для всех уровней подготовки").
                Build();

        addItem(item1);

        Training item2 = new CommercialTraining.CommercialTrainingBuilder().Name("TRX").
                StartTime(new Date(2019, 2, 8, 8, 30)).
                EndTime(new Date(2019, 2, 8, 9, 30)).
                GymName("Тренажерный зал").
                ProgramType("Специальные программы").
                LevelName("Для всех уровней подготовки").
                CoachName("Анастасия").
                CoachFamily("Молькова").
                Description("TRX - тренировка мышц всего тела с помощью уникального оборудования - " +
                        "TRX-петель. Это тренировка, которая позволяет не только развивать все мышечные группы, " +
                        "укреплять связки и сухожилия, но и развивать гибкость, ловкость, выносливость и многое " +
                        "другое. Данная тренировка имеет еще одно важное достоинство - эффективное развитие мышц так " +
                        "называемого кора(мышц-стабилизаторов). Упражнения подходят для всех возрастных групп, " +
                        "для мужчин и женщин, для лиц с отклонениями в состоянии здоровья, так как в этой тренировке " +
                        "нет никакой осевой (вертикальной) нагрузки на позвоночник").
                Capacity(10).
                Build();
        addItem(item2);

        Training item3 = new Training.TrainingBuilder().Name("New Body").
                StartTime(new Date(2019, 2, 8, 10, 00)).
                EndTime(new Date(2019, 2, 8, 10, 30)).
                GymName("Большой зал").
                ProgramType("Силовой и функциональный тренинг").
                LevelName("Для всех уровней подготовки").
                CoachName("Елена").
                CoachFamily("Куликова").
                IsNewTraining().
                Description("NEW BODY (55 мин) («Новое тело») - силовой урок, направленный на тренировку всех " +
                        "групп мышц. Специально подобранные комплексы упражнений помогут скорректировать проблемные зоны, " +
                        "независимо от того, каким телосложением вы обладаете. Урок рекомендован как для среднего так и для " +
                        "продвинутого уровня подготовки").
                Build();
        addItem(item3);

        Training item4 = new Training.TrainingBuilder().Name("ABS+Stretch").
                StartTime(new Date(2019, 2, 8, 16, 00)).
                EndTime(new Date(2019, 2, 8, 16, 30)).
                GymName("Большой зал").
                ProgramType("Mind&Body (Мягкий фитнес)").
                LevelName("Для всех уровней подготовки").
                CoachName("Елена").
                CoachFamily("Куликова").
                Replaced().
                Description("Урок, направленный на развитие гибкости, с использованием специальных упражнений на растягивание. " +
                        "Увеличивает подвижность суставов, эластичность связок, дает общее расслабление и релаксацию.").
                Build();
        addItem(item4);

        Training item5 = new Training.TrainingBuilder().Name("Pilates").
                StartTime(new Date(2019, 2, 8, 17, 30)).
                EndTime(new Date(2019, 2, 8, 18, 30)).
                GymName("Большой зал").
                LevelName("Для всех уровней подготовки").
                CoachName("Полина").
                CoachFamily("Соловьева").
                ProgramType("Mind&Body (Мягкий фитнес)").
                Description("Урок направлен на укрепление мышц-стабилизаторов, упражнгения пилатес " +
                        "способствуют снятию напряжению с позвоночника, восстановлению эластичности " +
                        "связочного аппарата и мышц. Урок рекомендован для всех уровней подготовки").
                IsPopular().
                Build();
        addItem(item5);

        Training item6 = new CommercialTraining.CommercialTrainingBuilder().Name("TRX").
                StartTime(new Date(2019, 2, 8, 18, 30)).
                EndTime(new Date(2019, 2, 8, 19, 30)).
                GymName("Тренажерный зал").
                LevelName("Для всех уровней подготовки").
                CoachName("Анастасия").
                ProgramType("Специальные программы").
                CoachFamily("Молькова").
                Description("TRX - тренировка мышц всего тела с помощью уникального оборудования - " +
                        "TRX-петель. Это тренировка, которая позволяет не только развивать все мышечные группы, " +
                        "укреплять связки и сухожилия, но и развивать гибкость, ловкость, выносливость и многое " +
                        "другое. Данная тренировка имеет еще одно важное достоинство - эффективное развитие мышц так " +
                        "называемого кора(мышц-стабилизаторов). Упражнения подходят для всех возрастных групп, " +
                        "для мужчин и женщин, для лиц с отклонениями в состоянии здоровья, так как в этой тренировке " +
                        "нет никакой осевой (вертикальной) нагрузки на позвоночник").
                Capacity(15).
                Build();
        addItem(item6);

        // addItem(new Training(new Date(2019,2,8,7,30),"Hatha Yoga","Большой зал","Низкая интенсивность","Елизарова Галина"));
        // addItem(new Training(new Date(2019,2,8,10,00),"TRX (платная секция) по записи","Тренажерный зал","Для всех уровней подготовки","Молькова Анастасия"));
        //addItem(new Training(new Date(2019,2,8,15,00),"New Body","Большой зал","Для всех уровней подготовки","Куликова Елена"));
        //addItem(new Training(new Date(2019,2,8,16,00),"ABS+Stretch","Большой зал","Для всех уровней подготовки","Куликова Елена"));
        // addItem(new Training(new Date(2019,2,8,17,30),"Pilates","Большой зал","Для всех уровней подготовки","Соловьева Полина"));
        //  addItem(new Training(new Date(2019,2,8,18,30),"TRX (платная секция) по записи","Тренажерный зал","Для всех уровней подготовки","Молькова Анастасия"));
        // Add some sample items.
        // for (int i = 1; i <= COUNT; i++) {

        //    addItem(new Training(new Date(2019,2,8,7,30),"Hatha Yoga","Большой зал","Низкая интенсивность","Елизарова Галина"));
        // }
    }

    private static void addItem(Training item) {
        ITEMS.add(item);
        //  ITEM_MAP.put(item.id, item);
    }

    //Создаем объект тренировки
//    private static Training createTrainingItem(int position) {
//        return new Training(String.valueOf(position), "Item " + position, makeDetails(position));
//    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }


}
