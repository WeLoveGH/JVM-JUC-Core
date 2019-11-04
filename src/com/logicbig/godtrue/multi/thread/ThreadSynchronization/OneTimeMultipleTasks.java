package com.logicbig.godtrue.multi.thread.ThreadSynchronization;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-25
 */
public class OneTimeMultipleTasks {
    public static void main (String[] args) {

        System.out.println("Main thread: " + Thread.currentThread());

        Timer timer = new Timer();

        final long start = System.currentTimeMillis();

        final TimerTask timerTask1 = new TimerTask() {
            @Override
            public void run () {
                System.out.print("Task1 invoked: " +
                        (System.currentTimeMillis()
                                - start) + " ms");
                System.out.println(" - " + Thread.currentThread());

                try {
                    /*

                    The task should finish quickly, otherwise it will delay other tasks, here
                    we are simulating a long running task to see the issue, if you remove this
                    the second task will run at its provided initial delay

                    */
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /*

                don't call cancel here cause it will
                terminate timer and second task won't run.

                */

                //timer.cancel();

            }
        };

        final TimerTask timerTask2 = new TimerTask() {
            @Override
            public void run () {
                System.out.print("Task2 invoked : " +
                        (System.currentTimeMillis()
                                - start) + " ms");
                System.out.println(" - " + Thread.currentThread());
                timer.cancel();
            }
        };

        timer.schedule(timerTask1, 1000);

       /*

       second submission of the same task will cause
       java.lang.IllegalStateException: Task already scheduled or cancelled
        timer.schedule(timerTask1, 2000);

      */

        timer.schedule(timerTask2, 2000);
    }
}