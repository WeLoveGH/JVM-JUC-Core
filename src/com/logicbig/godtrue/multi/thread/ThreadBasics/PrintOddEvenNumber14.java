package com.logicbig.godtrue.multi.thread.ThreadBasics;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description：create thread by runnable
 * <p>
 * 两个线程交替打印 1~100 的奇偶数
 * <p>
 * 思路：
 * 1：创建两个线程
 * 2：一个负责打印偶数，一个负责打印奇数
 * 3：通过 ReentrantLock 同步器控制两个线程的并发
 * 4：通过 sleep 方法控制两个线程的协作
 * 5：通过 (count&1)==0 判断为偶数，(count&1)==1 判断为奇数，位与运算性能更佳
 * 6：通过 while 和 ++ 控制变量的自增和阈值
 * <p>
 * 7：仔细思考一下这个程序还有什么优化空间？
 * @author：qianyingjie1
 * @create：2019-10-23
 */
public class PrintOddEvenNumber14 {

    private static int count = 0;

    private static final ReentrantLock reentrantLock = new ReentrantLock();

    private static final int MAX_VALUE = 100;

    private static final int SLEEP_TIMEOUT = 10;

    public static void main(String[] args) {

        new Thread(() -> {
            while (count <= MAX_VALUE) {
                reentrantLock.lock();
                if ((count & 1) == 0) {
                    System.out.println(Thread.currentThread().getName() + " " + count++);
                }

                try {
                    Thread.sleep(SLEEP_TIMEOUT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock.unlock();
            }
        }, "偶数").start();

        new Thread(() -> {
            while (count < MAX_VALUE) {
                reentrantLock.lock();
                if ((count & 1) == 1) {
                    System.out.println(Thread.currentThread().getName() + " " + count++);
                }

                try {
                    Thread.sleep(SLEEP_TIMEOUT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock.unlock();
            }
        }, "奇数").start();

    }
}

