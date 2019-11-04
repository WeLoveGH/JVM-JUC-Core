package com.codsjava.godtrue.multi.thread;

/**
 * @description：java中的并发问题
 * @author：qianyingjie1
 * @create：2019-10-21
 */
public class ConcurrencyIssuesInJava {
    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        testThread.start();
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        TestThread testThread2 = new TestThread();
        testThread2.setStop();
    }
}
class TestThread extends Thread {
    private boolean stop = false;

    @Override
    public void run() {
        while(!stop) {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public void setStop() {
        this.stop = true;
    }
}
