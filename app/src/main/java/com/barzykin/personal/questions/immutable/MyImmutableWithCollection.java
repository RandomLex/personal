package com.barzykin.personal.questions.immutable;

import java.util.ArrayList;
import java.util.List;

//1. final при объявлении класса
public final class MyImmutableWithCollection {

    // все поля должны быть final
    private final List<Integer> coordinates;

    // все поля передаём в конструкторе
    public MyImmutableWithCollection(List<Integer> coordinates) {
        this.coordinates = coordinates;
    }

    // не должно быть ни одного метода, изменяющего поля. (нет сеттеров, только геттеры)
    // если полем является коллекция или сложный объект, мы должны возвращать ссылку на копию этой коллекции или объекта
    public List<Integer> getCoordinates() {
        return new ArrayList<>(coordinates);
    }

    @Override
    public String toString() {
        return "MyImmutableWithCollection{" +
                "coordinates=" + coordinates +
                '}';
    }
}
