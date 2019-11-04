package com.logicbig.godtrue.multi.thread.ThreadBasics;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-24
 */
public class ThreadStates3 {

    public static void main(String[] args) throws InterruptedException {
        MyTask3 t = new MyTask3();
        t.setName("Thread3");
        ThreadStates.runExampleThread(t);
        synchronized (ThreadStates3.class) {
            ThreadStates3.class.notifyAll();
        }
    }

    private static class MyTask3 extends Thread {

        @Override
        public void run() {
            ThreadStates.printState("thread run() started", this);
            //using ThreadStates3.class object monitor
            synchronized (ThreadStates3.class) {
                try {
                    //this will put the thread in WAITING state
                    ThreadStates3.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ThreadStates.printState("thread finishing", this);
        }
    }
}