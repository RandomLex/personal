package com.barzykin.personal.questions.iterators;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class IteratorDemo {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(5);
        set.add(3);
        set.add(8);
        set.add(1);

        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            System.out.println(integer);
        }


    }
}
