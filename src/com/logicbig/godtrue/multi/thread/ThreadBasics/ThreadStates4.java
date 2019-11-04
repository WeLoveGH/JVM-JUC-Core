package com.logicbig.godtrue.multi.thread.ThreadBasics;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-24
 */
public class ThreadStates4 {

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            //acquiring lock on ThreadStates4.class object
            synchronized (ThreadStates4.class) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        MyTask4 t = new MyTask4();
        t.setName("Thread4");
        ThreadStates.runExampleThread(t);
    }

    private static class MyTask4 extends Thread {
        @Override
        public void run() {
            ThreadStates.printState("attempting to enter synchronized block", this);
            synchronized (ThreadStates4.class) {
                ThreadStates.printState("entered synchronized block", this);
                //do something
            }
            ThreadStates.printState("thread finishing", this);
        }
    }
}