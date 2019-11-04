package com.codsjava.godtrue.multi.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description：线程池示例
 * @author：qianyingjie1
 * @create：2019-10-22
 */
public class ThreadPoolInJava {

    public static void main(String[] args) {

        /**
         * 1:创建一个固定三个线程的线程池
         * 2：循环五次，创建五个线程，放入线程池中执行，
         *    这里注意，由于线程池固定的大小是 3 放入的任务有 5 个，会有连个任务放入等待的工作队列之中等待执行
         * 3：关闭线程池
         * 4：等待线程执行结束
         * 5：打印所有线程执行完毕
         */

        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            Runnable worker = new WorkerThread("" + i);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {   }

        System.out.println("All threads are finished.");
    }
}

class WorkerThread implements Runnable {
    private String message;
    public WorkerThread(String s){
        this.message=s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" (Start) message = "+message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" (End)");
    }
}


/*
Normally, server creates a new thread when a new request comes.
This approach have several disadvantages.
For creating a new thread for every new request will take more time and resources.
Thread pool concept is introduced to resolve these problems.

Thread pool in java

A thread pool represents a group of threads which are waiting the job and can be used many times.
In case of thread pool, when a new request comes then instead of creating a new thread, the job can be passed to thread pool.

Note: Thread pool helps us to limit the number of threads running in our application.
*/
