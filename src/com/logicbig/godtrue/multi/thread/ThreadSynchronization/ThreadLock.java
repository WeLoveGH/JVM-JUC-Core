package com.logicbig.godtrue.multi.thread.ThreadSynchronization;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-24
 */
public class ThreadLock {

    public static void main (String[] args) throws InterruptedException {

        ThreadLock demo = new ThreadLock();

        Thread thread1 = new Thread(() -> {
            System.out.println("thread1 before call "+ LocalDateTime.now());
            demo.syncMethod("from thread1");
            System.out.println("thread1 after call "+LocalDateTime.now());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("thread2 before call "+LocalDateTime.now());
            demo.syncMethod("from thread2");
            System.out.println("thread2 after call "+LocalDateTime.now());
        });

        thread1.start();
        thread2.start();
    }

    private synchronized void syncMethod (String msg) {
        System.out.println("in the sync method "+msg+" "+LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}