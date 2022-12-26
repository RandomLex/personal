package com.barzykin.personal.questions.enum_demo;

import java.sql.SQLOutput;

public class ColorsEnumDemo {
    public static void main(String[] args) {
        ColorsEnum color = ColorsEnum.RED;

        switch (color) {
            case RED:
                System.out.println("Стой");
                break;
            case YELLOW:
                System.out.println("Готовься");
                break;
            case GREEN:
                System.out.println("Иди");
                break;
        }

        System.out.println(color);

        ColorsEnum red = ColorsEnum.RED;
        ColorsEnum red1 = ColorsEnum.RED;
        ColorsEnum green = ColorsEnum.GREEN;

        System.out.println(red1 == red);
        System.out.println(red == green);

        System.out.println(green.ordinal());

        for (ColorsEnum value : ColorsEnum.values()) {
            System.out.println(value);
        }


    }
}
