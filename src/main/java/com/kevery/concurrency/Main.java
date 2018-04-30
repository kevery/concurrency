package com.kevery.concurrency;

public class Main {


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            out();
        }
    }


    public static void out() {
        ClazzAdd clazzAdd = new ClazzAdd();

        for (int i = 0; i < 5; i++) {
            new NewThread(clazzAdd).start();

        }


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("finally num is " + clazzAdd.sum);
    }

}

class NewThread extends Thread{
    private ClazzAdd clazzAdd;

    public NewThread(ClazzAdd clazzAdd) {
        this.clazzAdd = clazzAdd;
    }

    @Override
    public void run() {
        clazzAdd.add();
    }
}


class ClazzAdd {
    public int sum = 0;

    public void add() {

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sum += 1;
    }
}