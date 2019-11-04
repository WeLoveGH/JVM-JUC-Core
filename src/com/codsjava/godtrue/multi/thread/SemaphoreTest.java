package com.codsjava.godtrue.multi.thread;

import java.util.concurrent.Semaphore;

/**
 * @description：信号量示例
 *
 * 1：信号量中会有对应的凭证，线程获取到对应的凭证时才能继续执行，否则就处于等待状态
 * 2：凭证的个数是创建信号量时指定的
 * 3：尝试获取凭证的方法是 acquire ，当线程获取到凭证时，线程继续执行，凭证数减一
 * 5：当线程执行完毕的时候通过 release 释放凭证，这样其他阻塞获取凭证的线程就能被唤醒去获取凭证了，当线程释放凭证时凭证数加一
 *
 *
 * @author：qianyingjie1
 * @create：2019-10-21
 */

/**
 * 共享资源
 */
class Shared {
    static int count = 0;
}

/**
 * 线程类
 */
class TestSemaphoreThread extends Thread {

    /**
     * 信号量
     */
    Semaphore sem;

    /**
     * 线程名
     */
    String threadName;

    /**
     * 线程构造器
     * @param sem
     * @param threadName
     */
    public TestSemaphoreThread(Semaphore sem, String threadName) {
        super(threadName);
        this.sem = sem;
        this.threadName = threadName;
    }

    /**
     * 复写线程run方法，这是线程真正执行的方法，直接也能调用，不过最好不要调用，让jvm决定什么时候调用，jvm也是根据线程调度器来决定什么时候调用的
     */
    @Override
    public void run() {
        /**
         * 线程1的逻辑
         */
        if(this.getName().equals("Test1")) {
            System.out.println("Starting " + threadName);
            try {
                System.out.println(threadName + " is waiting for a permit.");
                //Acquiring the lock
                //尝试获取凭证，如果获取到可用的凭证，则线程可以继续，否则线程将进入阻塞状态，直到有可用的凭证为止
                sem.acquire();
                System.out.println(threadName + " gets a permit.");
                for(int i=0; i < 3; i++) {
                    Shared.count++;
                    System.out.println(threadName + ": " + Shared.count);
                    Thread.sleep(10);
                }
            } catch (InterruptedException exc) {
                System.out.println(exc);
            }

            //Release the permit.
            System.out.println(threadName + " releases the permit.");
            //释放凭证，线程可以继续走了
            sem.release();
        } else {//线程二的逻辑
            System.out.println("Starting " + threadName);
            try {
                System.out.println(threadName + " is waiting for a permit.");
                // acquiring the lock
                sem.acquire();
                System.out.println(threadName + " gets a permit.");
                for(int i=0; i < 3; i++)
                {
                    Shared.count--;
                    System.out.println(threadName + ": " + Shared.count);
                    Thread.sleep(10);
                }
            } catch (InterruptedException exc) {
                System.out.println(exc);
            }
            // Release the permit.
            System.out.println(threadName + " releases the permit.");
            sem.release();
        }
    }
}

/**
 * 信号量测试类
 */
public class SemaphoreTest {
    public static void main(String args[]) throws InterruptedException {

        /**
         * 创建一个信号量，只有一个凭证
         */
        Semaphore sem = new Semaphore(1);

        /**
         * 创建线程
         */
        TestSemaphoreThread TestSemaphoreThread1 = new TestSemaphoreThread(sem, "Test1");
        TestSemaphoreThread TestSemaphoreThread2 = new TestSemaphoreThread(sem, "Test2");

        /**
         * 启动线程
         */
        TestSemaphoreThread1.start();
        TestSemaphoreThread2.start();

        /**
         * 主线程等待其他线程执行完毕
         */
        TestSemaphoreThread1.join();
        TestSemaphoreThread2.join();

        System.out.println("count: " + Shared.count);
    }
}


/*

The java.util.concurrent.Semaphore class represents a counting semaphore.
It is used to control the access of shared resource with the help of a counter.
A semaphore is initialized with the given number which represents the number of permits.
If counter is greater than 0 then for each call to the acquire() method,
a permit is given to the calling thread and semaphore’s counter will be decremented by 1 otherwise the thread will be blocked until a permit can be acquired.
When a thread not required the shared resource, it will release the permit and counter will be incremented by 1.

Note: counter represents the number of threads which can access the shared resources at a time.

*/
