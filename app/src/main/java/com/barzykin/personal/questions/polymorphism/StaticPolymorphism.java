package com.barzykin.personal.questions.polymorphism;

class StaticPolymorphism {
    public static void main(String[] args) {
        int aInt = 3;
        int bInt = 4;

        String aStr = "Hello, ";
        String bStr = "Java!";

        System.out.println(plus(aInt, bInt));
        System.out.println(plus(aInt, bStr));
    }

    static int plus(int a, int b) {
        return a + b;
    }

    static String plus(int a, String b) {
        return a + b;
    }

//    static int plus(int x, int m) {
//        return x + m;
//    }
}
