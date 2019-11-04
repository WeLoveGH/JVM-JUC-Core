package com.logicbig.godtrue.multi.thread.ThreadSynchronization;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-24
 */
public class SimpleWaitNotify {

    private static String message;

    public static void main (String[] args) {

        Thread thread1 = new Thread(() -> {
            System.out.println(message);
        });

        Thread thread2 = new Thread(() -> {
            message = "A message from thread1";
        });

        thread1.start();
        thread2.start();
    }
}