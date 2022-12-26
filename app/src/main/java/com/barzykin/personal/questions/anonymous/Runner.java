package com.barzykin.personal.questions.anonymous;

class Runner {
    public static void main(String[] args) {
//        Parent p1 = new Parent(5);
//        p1.printXDecorated();

        Parent p2 = new Child(5);
        p2.printXDecorated();

        Parent p3 = new Parent() {
            private int y = 15;

            public void setY(int y) {
                this.y = y;
            }

            public int getY() {
                return y;
            }

            @Override
            public void printXDecorated() {
                System.out.println("*****" + super.getX() * y + "*****");
            }
        };

        p3.printXDecorated();


    }
}
