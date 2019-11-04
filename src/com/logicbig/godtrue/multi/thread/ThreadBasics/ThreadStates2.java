package com.logicbig.godtrue.multi.thread.ThreadBasics;

/**
 * @description：thread state
 * @author：qianyingjie1
 * @create：2019-10-24
 */
public class ThreadStates2 {
    public static void main(String[] args) {
        MyTask2 t = new MyTask2();
        t.setName("Thread2");
        ThreadStates.runExampleThread(t);
    }

    private static class MyTask2 extends Thread {
        @Override
        public void run() {
            ThreadStates.printState("thread run() started", this);
            try {
                //this will put the thread in TIMED_WAITING state
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ThreadStates.printState("thread finishing", this);
        }
    }
}