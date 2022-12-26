package com.barzykin.personal.questions.anonymous;

public class Child extends Parent {

    public Child() {
    }

    public Child(int x) {
        super(x);
    }

    public void printXDecorated() {
        System.out.println("+++++" + super.getX() + "+++++");
    }
}
