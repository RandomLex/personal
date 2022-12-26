package com.barzykin.personal.questions.super_inheritance;

class Child extends Parent {
    private String childField;

    protected String protectedField;

    public Child() {
    }

    public Child(String privateField, String protectedField, String childField) {
        super(privateField, protectedField);
        this.childField = childField;
    }

    void printProtectedField() {
        System.out.println(this.protectedField);
    }
}
