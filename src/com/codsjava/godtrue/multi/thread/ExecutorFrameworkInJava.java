package com.codsjava.godtrue.multi.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description：执行器框架示例
 * @author：qianyingjie1
 * @create：2019-10-22
 */
public class ExecutorFrameworkInJava {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            Runnable worker = new WorkerThread2("" + i);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {   }

        System.out.println("All threads are finished.");
    }
}

class WorkerThread2 implements Runnable {
    private String message;
    public WorkerThread2(String s){
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


Executor framework provides the facility to separates the task creation and its execution.
We have to pass Runnable objects to the executor and it is the responsibility of the executor to execute, instantiate and run with necessary threads.

Executor framework interfaces

Executor:
        It is used to submit a new task.
ExecutorService:
        it is a subinterface of Executor that adds methods to manage lifecycle of threads.
        It is used to run the submitted tasks and methods to produce a Future to get a result from an asynchronous computation.
ScheduledExecutorService:
        It is a subinterface of ExecutorService, to execute commands after a given delay.

How to create an executor?

The Executors classes are used to create the executor.

// It creates a single thread ExecutorService
ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();

//It creates a single thread ScheduledExecutorService
ScheduledExecutorService singleScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

//It creates an ExecutorService that use a pool of 5 threads
ExecutorService fixedExecutorService = Executors.newFixedThreadPool(5);

//It creates an ExecutorService that use a pool that creates threads on demand and kill them after 60 seconds if they are not used
ExecutorService onDemandExecutorService = Executors.newCachedThreadPool();

//It creates a ScheduledExecutorService that use a pool of 5 threads
ScheduledExecutorService fixedScheduledExecutorService = Executors.newScheduledThreadPool(5);

*/
