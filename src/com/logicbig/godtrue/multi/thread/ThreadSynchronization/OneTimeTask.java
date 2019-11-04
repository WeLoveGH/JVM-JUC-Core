package com.logicbig.godtrue.multi.thread.ThreadSynchronization;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-25
 */
public class OneTimeTask {

    public static void main(String[] args) {

        System.out.println("Main thread: " + Thread.currentThread());

        Timer timer = new Timer();

        final long start = System.currentTimeMillis();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                System.out.print("Task invoked: " +
                        (System.currentTimeMillis() - start) + " ms");

                System.out.println(" - " + Thread.currentThread());

                timer.cancel();
            }
        }, 1000);

    }
}
