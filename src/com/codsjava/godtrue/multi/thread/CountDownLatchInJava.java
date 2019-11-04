package com.codsjava.godtrue.multi.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @description：CountDownLatch示例
 * @author：qianyingjie1
 * @create：2019-10-22
 */
public class CountDownLatchInJava {
    public static void main(String args[]) throws InterruptedException {
        /**
         * 创建倒数计算器门闩，可以调整计数器的值，来观察倒数计算器门闩的控制效果
         */
        CountDownLatch latch = new CountDownLatch(3);

        /**
         * 创建线程
         */
        Employee first = new Employee(500, latch, "Employee-1");
        Employee second = new Employee(1000, latch, "Employee-2");
        Employee third = new Employee(1500, latch, "Employee-3");

        /**
         * 启动线程
         */
        first.start();
        second.start();
        third.start();

        /**
         * 通过倒数计算器门闩，控制线程的执行，由于计算器初始值为3，所以，要等待三个线程都执行完了，才能继续往下走
         *
         * 这个功能类似 join 方法，等待其他线程执行完成，主线程再继续执行，也就是计算器为 0 时，主线程才继续执行
         *
         */
        latch.await();

        System.out.println(Thread.currentThread().getName() + " has finished");
    }
}

class Employee extends Thread {
    /**
     * 模拟逻辑的执行时间
     */
    private int delay;

    /**
     * 倒数计数器门闩
     */
    private CountDownLatch latch;

    /**
     * 线程构造器
     * @param delay
     * @param latch
     * @param name
     */
    public Employee(int delay, CountDownLatch latch, String name) {
        super(name);
        this.delay = delay;
        this.latch = latch;
    }

    /**
     * 复写线程的run方法
     */
    @Override
    public void run() {
        try {
            /**
             * 线程休眠
             */
            Thread.sleep(delay);

            /**
             * 倒数计数器门闩，进行计数，进行减数操作，当某个线程执行完毕后，进行减数操作，当计数器减为 0 时，主线程便可以继续执行，不在处于阻塞状态
             */
            latch.countDown();

            System.out.println(Thread.currentThread().getName() + " finished");
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
/*

The java.util.concurrent.CountDownLatch class allows a thread to wait for other threads to complete.
It is used in the situations when one service is only starts when all other required services have started.
Here count represents the number of threads, for which latch should wait.
It can only be set once and we cannot modify it.
All these threads required to do count down by calling CountDownLatch.countDown() when they are completed or ready to perform the job.

*/

