package com.codsjava.godtrue.multi.thread;

/**
 * @description：线程命名
 * @author：qianyingjie1
 * @create：2019-10-17
 */
class Test extends Thread{
    @Override
    public void run(){
        //Thread.currentThread()returns the current thread.
        System.out.println("Id of running thread: " +
                Thread.currentThread().getId() +
                " Name of running thread: " +
                Thread.currentThread().getName());
    }
}

public class ThreadExample {
    public static void main(String args[]){
        //creating thread.
        Test thrd1 = new Test();
        Test thrd2 = new Test();

        //set thread name.
        thrd1.setName("My Thread1");
        thrd2.setName("My Thread2");

        //start the thread.
        thrd1.start();
        thrd2.start();
    }
}
