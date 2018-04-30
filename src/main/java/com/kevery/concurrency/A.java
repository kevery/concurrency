package com.kevery.concurrency;

public class A {
    public static int x = 0;
    public static int y = 0;

    public static int a = 0;
    public static int b = 0;

    public static void main(String[] args) {
        A1 a1 = new A1();
        a1.start();
        A2 a2 = new A2();
        a2.start();

        System.out.println("x=" + x + "y=" + y);
        System.out.println("a=" + a + "b=" + b);
    }


}


class A1 extends Thread {
    @Override
    public void run() {
        A.a = 1;
        A.x = A.b;
    }
}

class A2 extends Thread {

    @Override
    public void run() {
        A.b = 2;
        A.y = A.a;
    }
}