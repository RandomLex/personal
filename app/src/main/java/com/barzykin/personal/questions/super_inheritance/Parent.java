package com.barzykin.personal.questions.super_inheritance;

class Parent {
    private String privateField;

    protected String protectedField;

    public Parent() {
    }

    public Parent(String privateField, String protectedField) {
        this.privateField = privateField;
        this.protectedField = protectedField;
    }

    public String getPrivateField() {
        return privateField;
    }

    public void setPrivateField(String privateField) {
        this.privateField = privateField;
    }

    public String getProtectedField() {
        return protectedField;
    }

    public void setProtectedField(String protectedField) {
        this.protectedField = protectedField;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "privateField='" + privateField + '\'' +
                ", protectedField='" + protectedField + '\'' +
                '}';
    }
}
