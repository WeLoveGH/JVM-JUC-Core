package com.codsjava.godtrue.multi.thread;

/**
 * @description：当运行的线程都是守护线程的时候，JVM会自动退出
 * @author：qianyingjie1
 * @create：2019-10-18
 */
public class DaemonThreadExample3 {
    public static void main(String[] args) {

/*        Thread t = new T12();
        //t.setDaemon(true);

        t.setDaemon(false);

        t.start();*/

    }
}

class T12 extends Thread{
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + " "
                + Thread.currentThread().isDaemon());
    }
}


/*

Non-Static synchronized method
    Lock is on object.
    Commonly used.
    Static keyword is not used

Static synchronized method
    Lock is on class.
    Not commonly used.
    Used with static keyword.

*/
