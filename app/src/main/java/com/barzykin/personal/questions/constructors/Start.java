package com.barzykin.personal.questions.constructors;

public class Start {
    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        System.out.println(p1);
        p1.setX(3);
        p1.setY(4);
        System.out.println(p1);

        Point p2 = p1;

        Point p3 = new Point(p1);
        System.out.println();
    }
}
