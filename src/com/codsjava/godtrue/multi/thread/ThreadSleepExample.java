package com.codsjava.godtrue.multi.thread;

/**
 * @description：sleep方法的使用
 * @author：qianyingjie1
 * @create：2019-10-18
 */
class T14 extends Thread{
    @Override
    public void run(){
        for(int i=1;i<=5;i++){
            try{

                /**
                 *
                 * 由于某种原因，需要等待若干时间，然后继续执行
                 * sleep不会释放锁，会进入阻塞状态
                 *
                 * 中断，或者休眠时间到期，可以唤醒线程
                 *
                 */

                Thread.sleep(600);
            }catch(Exception e){
                System.out.println(e);
            }
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
    }
}

public class ThreadSleepExample {
    public static void main(String args[]){
        //creating thread.
        T14 thrd1 = new T14();
        T14 thrd2 = new T14();
        T14 thrd3 = new T14();

        //start threads.
        thrd1.start();
        thrd2.start();
        thrd3.start();
    }
}
