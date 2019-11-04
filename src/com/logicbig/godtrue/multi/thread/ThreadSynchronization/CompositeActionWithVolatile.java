package com.logicbig.godtrue.multi.thread.ThreadSynchronization;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-25
 */
public class CompositeActionWithVolatile {

    static volatile int c;

    public static void main(String[] args) throws InterruptedException {

        for (int t = 0; t < 10; t++) {
            c = 0;

            Thread thread1 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    c++;
                }
            });

            Thread thread2 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    c++;
                }
            });

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            System.out.println("counter value = " + c);
        }

    }
}