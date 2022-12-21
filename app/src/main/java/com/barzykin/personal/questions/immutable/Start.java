package com.barzykin.personal.questions.immutable;

import java.util.ArrayList;
import java.util.List;

class Start {
    public static void main(String[] args) {
        String a = "1";
        a = a + "2";
        System.out.println(a);
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer();


        System.out.println("------------");
        List<Integer> coordinates = new ArrayList<>();
        coordinates.add(3);
        coordinates.add(4);
        MyImmutableWithCollection imwc = new MyImmutableWithCollection(coordinates);
        List<Integer> imwcCoordinates = imwc.getCoordinates();
        System.out.println(imwc);
        imwcCoordinates.add(5);
        System.out.println(imwc);
    }
}
