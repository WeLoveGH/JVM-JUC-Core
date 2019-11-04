package com.codsjava.godtrue.multi.thread;

/**
 * @description：线程的权限
 * @author：qianyingjie1
 * @create：2019-10-17
 */
class T8 extends Thread{
    @Override
    public void run(){
        System.out.println("Priority of running thread: " +
                Thread.currentThread().getPriority());
    }
}

public class ThreadPriorityExample {
    public static void main(String args[]){
        //creating thread.
        T8 thrd1 = new T8();
        T8 thrd2 = new T8();
        T8 thrd3 = new T8();

        //set thread priority.
        thrd1.setPriority(Thread.MIN_PRIORITY);
        thrd2.setPriority(Thread.NORM_PRIORITY);
        thrd3.setPriority(Thread.MAX_PRIORITY);

        //start the thread.
        thrd1.start();
        thrd2.start();
        thrd3.start();
    }
}