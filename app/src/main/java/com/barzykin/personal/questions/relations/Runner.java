package com.barzykin.personal.questions.relations;

import java.sql.SQLOutput;

class Runner {

    public static void main(String[] args) {
        Auto.Engine standard = new Auto.Engine(100, 2.5);
        Auto reno = new Auto("Reno", standard);
        System.out.println(reno);
    }

}
