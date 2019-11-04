package com.codsjava.godtrue.multi.thread;

/**
 * @description：能否直接调用线程的run方法
 * @author：qianyingjie1
 * @create：2019-10-17
 */
public class CanCallRun {
    public static void main(String[] args) {

        /**
         *
         * 我们可以直接调用线程的run方法，这是没问题的
         * 不过和jvm来调用run方法的不同点在于，
         * 我们直接调用线程的run方法，没有创建一个新的线程调用栈，run方法的运行是在当前线程的调用栈之内的
         *
         */
        Thread t = new MyThreadForT7();
        t.run();

    }
}

class MyThreadForT7 extends Thread{
}

