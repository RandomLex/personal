package com.barzykin.personal.questions.anonymous;

public abstract class Parent {
    private int x;

    public Parent() {
    }

    public Parent(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public abstract void printXDecorated();
//    {
//        System.out.println("-----" + x + "-----");
//    }

    @Override
    public String
    toString() {
        return "Parent{" +
                "x=" + x +
                '}';
    }
}
