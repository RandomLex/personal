package com.barzykin.personal.questions.immutable;

public final class MyImmutable {
    private final int x;
    private final int y;

    public MyImmutable(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
