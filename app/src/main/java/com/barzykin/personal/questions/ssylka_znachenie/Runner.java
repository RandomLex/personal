package com.barzykin.personal.questions.ssylka_znachenie;

class Runner {
    public static void main(String[] args) {
        int a = 3;
        int c = plus(a, 4);
        int c1 = plus(5, 8);
        System.out.println(c);
        System.out.println(c1);
        System.out.println(a);

        System.out.println("-------------");

        MyInt aInt = new MyInt(3);
        MyInt bInt = new MyInt(4);

        int cInt = plus(aInt, bInt);
        System.out.println(cInt);
        System.out.println(aInt);
    }

    private static int plus(int a, int b) {
        a = a + 10;
        return a + b;
    }

    private static int plus(MyInt a, MyInt b) {
        a.setValue(a.getValue() + 10);
        return a.getValue() + b.getValue();
    }

}
