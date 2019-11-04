package com.codsjava.godtrue.multi.thread;

/**
 * @description：线程组示例
 * @author：qianyingjie1
 * @create：2019-10-22
 */
public class ThreadGroupInJava implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" , "+Thread.currentThread().getThreadGroup());
    }

    public static void main(String[] args) throws InterruptedException{

        ThreadGroupInJava threadGroupTest = new ThreadGroupInJava();

        ThreadGroup threadGroup = new ThreadGroup("Parent Thread Group");

        Thread t1 = new Thread(threadGroup, threadGroupTest,"T1");
        t1.start();
        t1.join();

        Thread t2 = new Thread(threadGroup, threadGroupTest,"T2");
        t2.start();
        t2.join();

        Thread t3 = new Thread(threadGroup, threadGroupTest,"T3");
        t3.start();
        t3.join();

        System.out.println("Thread Group Name: "+threadGroup.getName());

        threadGroup.list();
    }
}

/*

The java.lang.ThreadGroup class provides the facility to create the thread group.
Thread group provides a way to group multiple threads in a single unit.
In this way, multiple threads can be suspended, resume or interrupted as a single unit.

Note:

A thread is can access the information about its own thread group but cannot access information about its thread group’s parent thread group or any other thread group.
A thread group creates a tree in which every thread group except the initial thread group has a parent.

*/
