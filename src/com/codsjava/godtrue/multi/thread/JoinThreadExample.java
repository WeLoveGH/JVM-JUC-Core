package com.codsjava.godtrue.multi.thread;

/**
 * @description：使用join方法的示例
 * @author：qianyingjie1
 * @create：2019-10-17
 */
class T9 extends Thread{
    @Override
    public void run(){
        for(int i=1;i<=5;i++){
            try{
                Thread.sleep(600);
            }catch(Exception e){
                System.out.println(e);
            }
            System.out.println(Thread.currentThread().getName()+"-"+i);
        }
    }
}

public class JoinThreadExample {
    public static void main(String args[]){


        /**
         *
         * join方法的含义是等待其他线程执行完毕后，继续执行当前线程
         *
         * 当等待其他线程执行的时候，当前线程的状态处于等待状态
         * 直到发生如下三种情况，当前线程才继续执行
         * 1：等待的线程执行完毕
         * 2：等待的线程执行超时，如果使用了带超时参数的等待方法
         * 3：等待的线程被中断，这个观点有点验证？？？
         *
         */

        //creating thread.
        T9 thrd1 = new T9();
        T9 thrd2 = new T9();
        T9 thrd3 = new T9();

        thrd1.start();
        try{
            //thrd1.join();
            //thrd1.join(1800);
            thrd1.join();
        }catch(Exception e){
            System.out.println(e);
        }


        thrd2.start();
        thrd2.interrupt();

/*        try{
            thrd2.join();
        }catch(Exception e){
            System.out.println(e);
        }*/

        thrd3.start();
    }
}
