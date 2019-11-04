package com.codsjava.godtrue.multi.thread;

/**
 * @description：能否重复调用start方法
 * @author：qianyingjie1
 * @create：2019-10-17
 */
public class CallStartTwice2 {
    public static void main(String[] args) {

        Thread t = new MyThreadForT6();
        t.start();
        //当再次调用一个线程的start方法时，会抛出 java.lang.IllegalThreadStateException
        //因为线程启动时，会判断线程的状态是否为0，为0，则继续启动，否则证明已经调用过start方法，线程的状态已经做出了调整，则抛出线程状态非法的异常
        t.start();
    }
}

class MyThreadForT6 extends Thread{
}

