package com.codsjava.godtrue.multi.thread;

/**
 * @description：设置线程为守护线程
 * @author：qianyingjie1
 * @create：2019-10-18
 */
class T10 extends Thread{
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + " "
                + Thread.currentThread().isDaemon());
    }
}

public class DaemonThreadExample1 {
    public static void main(String args[]){
        //creating thread.
        T10 thrd1 = new T10();
        T10 thrd2 = new T10();

        //set names
        thrd1.setName("My Thread1");
        thrd2.setName("My Thread2");

        //set thrd1 as daemon thread.
        thrd1.setDaemon(true);

        //start threads.
        thrd1.start();
        thrd2.start();
    }
}