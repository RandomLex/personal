package com.barzykin.personal.questions.head_elements;

public class Runner {
    public static void main(String[] args) {
        SomeClass.InnerStaticClass innerStaticClass = new SomeClass.InnerStaticClass();

        SomeClass someClass = new SomeClass();
        SomeClass.InnerClass innerClass = someClass.new InnerClass();
    }
}
