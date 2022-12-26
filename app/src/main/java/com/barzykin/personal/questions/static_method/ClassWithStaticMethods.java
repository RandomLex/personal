package com.barzykin.personal.questions.static_method;

public class ClassWithStaticMethods {
    public static void main(String[] args) {
        printA();
        ClassWithStaticMethods.printA();

        final int x = 10;
//        x = 25;
//        x = 2;


        ClassWithStaticMethods classWithStaticMethods = new ClassWithStaticMethods();
        classWithStaticMethods.printB();
    }

    static void printA() {
        System.out.println("A");
//        printB();
    }

    void printB() {
        System.out.println("B");
        printA();
    }

}
