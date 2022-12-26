package com.barzykin.personal.questions.enum_demo;

public enum ColorsEnum {
    RED("красный", 0),
    YELLOW("желтый", 1),
    GREEN("зелёный", 2);

    private String russianColor;
    private int place;


    ColorsEnum(String russianColor, int place) {
        this.russianColor = russianColor;
        this.place = place;
    }

    @Override
    public String toString() {
        return "ColorsEnum{" +
                "russianColor='" + russianColor + '\'' +
                ", place=" + place +
                '}';
    }
}
