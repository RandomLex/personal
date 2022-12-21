package com.barzykin.personal.questions.stack_trace;

class Start {
    public static void main(String[] args) {
        methodA();
    }

    static void methodA() {
        methodB();
    }

    static void methodB() {
        throw new RuntimeException();
//        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
//        for (StackTraceElement stackTraceElement : stackTrace) {
//            System.out.println(stackTraceElement.getMethodName());
//        }

    }
}
