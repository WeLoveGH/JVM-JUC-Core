package com.codsjava.godtrue.multi.thread;

/**
 * @description：死锁的示例
 * @author：qianyingjie1
 * @create：2019-10-18
 */
public class DeadLockExample {

    public static void main(String[] args) {
        final String resource1 = "Amani";
        final String resource2 = "Nidhi";
        final String resource3 = "Prabhjot";

        Thread thread1 = new Thread() {

            @Override
            public void run() {
                //thread1 tries to lock on resource1.
                synchronized (resource1) {
                    System.out.println("Thread1 locked resource1: " + resource1);

                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    //thread1 tries to lock on resource2.
                    synchronized (resource2) {
                        System.out.println("Thread2 locked resource2: " + resource2);
                    }

                    //thread1 tries to lock on resource3.
                    synchronized (resource3) {
                        System.out.println("Thread2 locked resource3: " + resource3);
                    }
                }
            }
        };

        Thread thread2 = new Thread() {

            @Override
            public void run() {
                //thread1 tries to lock on resource2.
                synchronized (resource2) {
                    System.out.println("Thread1 locked resource2: " + resource2);

                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    //thread1 tries to lock on resource1.
                    synchronized (resource1) {
                        System.out.println("Thread2 locked resource1: " + resource1);
                    }

                    //thread1 tries to lock on resource3.
                    synchronized (resource3) {
                        System.out.println("Thread2 locked resource3: " + resource3);
                    }
                }
            }
        };

        Thread thread3 = new Thread() {

            @Override
            public void run() {
                //thread1 tries to lock on resource3.
                synchronized (resource3) {
                    System.out.println("Thread1 locked resource3: " + resource3);

                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    //thread1 tries to lock on resource1.
                    synchronized (resource1) {
                        System.out.println("Thread2 locked resource1: " + resource1);
                    }

                    //thread1 tries to lock on resource2.
                    synchronized (resource2) {
                        System.out.println("Thread2 locked resource2: " + resource2);
                    }
                }
            }
        };

        //start threads.
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
