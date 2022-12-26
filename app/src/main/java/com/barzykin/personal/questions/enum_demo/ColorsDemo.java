package com.barzykin.personal.questions.enum_demo;

public class ColorsDemo {
    public static void main(String[] args) {
        String input = "YELLOW";

        switch (input) {
            case Colors.RED:
                System.out.println("Стой");
                break;
            case Colors.YELLOW:
                System.out.println("Готовься");
                break;
            case Colors.GREEN:
                System.out.println("Иди");
                break;
            default:
                throw new IllegalArgumentException("Цвет должен быть цветом светофора");
        }

    }
}
