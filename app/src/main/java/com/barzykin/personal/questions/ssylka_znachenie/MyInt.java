package com.barzykin.personal.questions.ssylka_znachenie;

class MyInt {
    private int value;

    public MyInt(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
