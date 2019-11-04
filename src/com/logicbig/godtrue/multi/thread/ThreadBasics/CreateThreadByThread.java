package com.logicbig.godtrue.multi.thread.ThreadBasics;

/**
 * @description：create thread by thread
 * @author：qianyingjie1
 * @create：2019-10-23
 */
public class CreateThreadByThread {

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();

        Thread thread2 = new MyThread();
        thread2.start();

        System.out.println(Thread.currentThread()
                .getName());
    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread()
                        .getName() + ": " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
