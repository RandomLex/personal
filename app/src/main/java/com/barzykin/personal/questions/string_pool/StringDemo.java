package com.barzykin.personal.questions.string_pool;

import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.Arrays;

public class StringDemo {
    public static void main(String[] args) throws InterruptedException {
        String a = "ლ";
        String a1 = "a";
        String a2 = new String("a");
        a2.intern();

        System.out.println(a == a2);

        String a3 = new AnotherClass().a;

        String b = "b";
        String c = "c";
        String[] strings = {c, b, a};
        printArray(strings);
        Arrays.sort(strings);
        printArray(strings);

        char[] chars = b.toCharArray();
        char ch = chars[0];
        System.out.println(ch);

        byte[] bytes = a.getBytes(StandardCharsets.UTF_8);
        for (byte aByte : bytes) {
            System.out.println(aByte);
        }
        String s = new String(bytes);
        System.out.println(s);


    }

    static void printArray(String[] strings) {
        Arrays.stream(strings).forEach(System.out::print);
        System.out.println();
    }
}
