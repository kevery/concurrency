package com.kevery.concurrency;

public class Example {
    private int a = 0;
    private boolean flag = false;


    public  void write() {
        a = 1;
        flag = true;
    }

    public  void read() {
        if (flag) {
            int i = a;
            System.out.println(i);
        } else {
            System.out.println(0);
        }
    }


}


class MyThread1 implements Runnable {
    private Example example;

    public MyThread1(Example example) {
        this.example = example;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("开始写");
        example.write();

    }
}

class MyThread2 implements Runnable {
    private Example example;

    public MyThread2(Example example) {
        this.example = example;
    }

    @Override
    public void run() {
        System.out.println("开始读");
        example.read();
    }


    public static void main(String[] args) {
            Example example = new Example();
            Thread thread1 = new Thread(new MyThread1(example));
            Thread thread2 = new Thread(new MyThread2(example));
            thread1.start();
            thread2.start();

            System.out.println("--------------------------------");


    }
}