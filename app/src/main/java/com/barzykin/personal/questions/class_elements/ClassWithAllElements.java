package com.barzykin.personal.questions.class_elements;

class ClassWithAllElements {
    private static final int CONSTANT = 100;
    private static int staticField;
    private int field;

    static {
        System.out.println("static init block");
    }

    {
        System.out.println("Object is created");

    }

    public ClassWithAllElements() {
        System.out.println("Constructor");
    }

    public ClassWithAllElements(int field) {
        this.field = field;
    }

    public static int getStaticField() {
        return staticField;
    }

    public static void setStaticField(int staticField) {
        ClassWithAllElements.staticField = staticField;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }
}
