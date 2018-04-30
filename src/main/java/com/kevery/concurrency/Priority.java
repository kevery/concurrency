package com.kevery.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Priority {
    public static volatile boolean noStart = true;
    public static volatile boolean noEnd = true;


    static class Job implements Runnable {
        private int priority;
        private int jobCount;

        public Job(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            while (noStart) {
                Thread.yield();
            }
            while (noEnd) {
                Thread.yield();
                jobCount++;
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobs.add(job);
            Thread thread = new Thread(job, "Thread:" + i);
            thread.setPriority(priority);
            thread.start();
            System.out.println("test");
            System.out.println("test");
            System.out.println();
        }


        noStart = false;
        TimeUnit.SECONDS.sleep(10);
        noEnd = false;
        for (Job job : jobs) {
            System.out.println("Job priority:" + job.priority + ",Count:" + job.jobCount);
        }

    }
}
