package com.barzykin.personal.questions.relations;

class Auto {
    private String name;
    private Engine engine;

    public Auto(String name, Engine engine) {
        this.name = name;
        this.engine = engine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "name='" + name + '\'' +
                ", engine=" + engine +
                '}';
    }

     static class Engine {
        private int power;
        private double volume;

        public Engine(int power, double volume) {
            this.power = power;
            this.volume = volume;
        }

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }

        public double getVolume() {
            return volume;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }

        @Override
        public String toString() {
            return "Engine{" +
                    "power=" + power +
                    ", volume=" + volume +
                    '}';
        }
    }

}
