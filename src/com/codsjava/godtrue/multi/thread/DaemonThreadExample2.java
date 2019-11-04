package com.codsjava.godtrue.multi.thread;

/**
 * @description：测试启动之后设置线程的类型
 * @author：qianyingjie1
 * @create：2019-10-18
 */
class T11 extends Thread{
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + " "
                + Thread.currentThread().isDaemon());
    }
}

public class DaemonThreadExample2 {
    public static void main(String args[]){
        //creating thread.
        T11 thrd1 = new T11();
        T11 thrd2 = new T11();

        //set names
        thrd1.setName("My Thread1");
        thrd2.setName("My Thread2");

        //start thrd1.
        thrd1.start();

        /**
         * This method must be invoked before the thread is started
         */

        //set thrd1 as daemon thread.
        //java.lang.IllegalThreadStateException here.
        thrd1.setDaemon(true);

        //start thread2.
        thrd2.start();
    }
}
