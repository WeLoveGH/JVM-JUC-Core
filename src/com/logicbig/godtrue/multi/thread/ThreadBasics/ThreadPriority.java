package com.logicbig.godtrue.multi.thread.ThreadBasics;

import java.util.concurrent.TimeUnit;

/**
 * @description：线程权限
 * @author：qianyingjie1
 * @create：2019-10-24
 */
public class ThreadPriority {

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.setName("Thread.MIN_PRIORITY");
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();

        Thread thread2 = new MyThread();
        thread2.setName("Thread.MAX_PRIORITY");
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread2.start();
    }

    private static class MyThread extends Thread {
        private int c;

        @Override
        public void run() {
            String threadName = Thread.currentThread()
                    .getName();

            System.out.println(threadName + " started.");
            for (int i = 0; i < 1000; i++) {
                c++;
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(threadName + " ended.");
        }
    }
}


/*

Thread Priorities are used on competing threads and their behavior varies from OS to OS.
We shouldn't make coding decisions based on thread priorities

*/
