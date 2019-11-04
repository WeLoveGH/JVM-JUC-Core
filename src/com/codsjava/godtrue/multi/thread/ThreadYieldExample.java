package com.codsjava.godtrue.multi.thread;

/**
 * @description：测试yield方法的使用
 * @author：qianyingjie1
 * @create：2019-10-18
 */
class T13 extends Thread{
    @Override
    public void run(){
        for(int i=1;i<=5;i++){
            try{
                /**
                 *
                 * Causes the currently executing thread object to temporarily pause for giving a chance to the remaining waiting threads of the same priority to execute.
                 * If no such thread than same thread will continue its execution.

                 If multiple threads of same priority are in waiting state then it depends on thread scheduler which thread have to be executed.
                 The thread which stops executing will be in the runnable state and it will also depends upon thread scheduler when it will get chance.

                 yield方法的作用：
                 当前正在执行的线程让出cup时间片，自己也进行可执行状态，和其他线程一起再次进行下一轮的cpu时间片

                 让渡出自己的cpu时间片给其他线程使用

                 *
                 */
                Thread.yield();
                //try{Thread.sleep(100);}catch (Exception e){e.printStackTrace();}
            }catch(Exception e){
                System.out.println(e);
            }
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
    }
}

public class ThreadYieldExample {
    public static void main(String args[]){
        //creating thread.
        T13 thrd1 = new T13();
        T13 thrd2 = new T13();
        T13 thrd3 = new T13();

        //start threads.
        thrd1.start();
        thrd2.start();
        thrd3.start();
    }
}
