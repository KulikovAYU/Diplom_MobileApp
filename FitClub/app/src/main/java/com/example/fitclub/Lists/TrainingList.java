package com.example.fitclub.Lists;

import com.example.fitclub.Models.Training;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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

        addItem(new Training(new Date(2019,2,8,7,30),"Hatha Yoga","Большой зал","Низкая интенсивность","Елизарова Галина"));
        addItem(new Training(new Date(2019,2,8,10,00),"TRX (платная секция) по записи","Тренажерный зал","Для всех уровней подготовки","Молькова Анастасия"));
        addItem(new Training(new Date(2019,2,8,15,00),"New Body","Большой зал","Для всех уровней подготовки","Куликова Елена"));
        addItem(new Training(new Date(2019,2,8,16,00),"ABS+Stretch","Большой зал","Для всех уровней подготовки","Куликова Елена"));
        addItem(new Training(new Date(2019,2,8,17,30),"Pilates","Большой зал","Для всех уровней подготовки","Соловьева Полина"));
        addItem(new Training(new Date(2019,2,8,18,30),"TRX (платная секция) по записи","Тренажерный зал","Для всех уровней подготовки","Молькова Анастасия"));
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
