package com.logicbig.godtrue.multi.thread.ThreadSynchronization;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-24
 */
public class SimpleWaitNotify4 {

    public static void main (String[] args) {

        SharedObject obj = new SharedObject();

        Thread thread1 = new Thread(() -> {
            obj.printMessage();
        });

        Thread thread2 = new Thread(() -> {
            obj.setMessage("A message from thread1");
        });

        thread1.start();
        thread2.start();
    }

    private static class SharedObject {
        private String message;

        public synchronized void setMessage (String message) {
            this.message = message;
            notify();
        }

        public synchronized void printMessage () {
            while (message == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(message);

        }
    }
}