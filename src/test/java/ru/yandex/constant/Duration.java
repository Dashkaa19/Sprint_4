package ru.yandex.constant;

import java.util.Random;

public class Duration {
    public static final String ONE_DAY = "сутки";
    public static final String TWO_DAYS = "двое суток";
    public static final String THREE_DAYS = "трое суток";
    public static final String FOUR_DAYS = "четверо суток";
    public static final String FIVE_DAYS = "пятеро суток";
    public static final String SIX_DAYS = "шестеро суток";
    public static final String SEVEN_DAYS = "семеро суток";


    public static String getRandomDuration() {

        String[] durations = {
                ONE_DAY, TWO_DAYS, THREE_DAYS, FOUR_DAYS,
                FIVE_DAYS, SIX_DAYS, SEVEN_DAYS
        };


        Random random = new Random();
        int randomIndex = random.nextInt(durations.length);


        return durations[randomIndex];
    }
}
