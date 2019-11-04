package com.codsjava.godtrue.multi.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description：可重入锁示例
 * @author：qianyingjie1
 * @create：2019-10-21
 */
public class ReentrantLockTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        //ReentrantLockMethodsCounter lockMethodsCounter = new ReentrantLockMethodsCounter();

        ReentrantLockCounter reentrantLockCounter = new ReentrantLockCounter();

        executorService.submit(() -> {
/*            System.out.println("IncrementCount (First Thread) : " +
                    lockMethodsCounter.incrementAndGetCounter());*/

            System.out.println("IncrementCount (First Thread) : " +
                    reentrantLockCounter.increment());
        });

        executorService.submit(() -> {
/*            System.out.println("IncrementCount (Second Thread) : " +
                    lockMethodsCounter.incrementAndGetCounter());*/

            System.out.println("IncrementCount (Second Thread) : " +
                    reentrantLockCounter.increment());
        });

        executorService.shutdown();
    }
}


class ReentrantLockMethodsCounter {
    private final ReentrantLock lock = new ReentrantLock();
    private int count = 0;
    public int incrementAndGetCounter() {
        boolean isAcquired = lock.tryLock();
        System.out.println("Lock Acquired : " + isAcquired);
        if(isAcquired) {
            try {
                Thread.sleep(1000);
                count = count + 1;
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            } finally {
                lock.unlock();
            }
        }
        return count;
    }
}


class ReentrantLockCounter {
    private final ReentrantLock lock = new ReentrantLock();
    private int count = 0;
    //Use Lock For Thread Safe Increment
    public int increment() {
        lock.lock();
        try {
            count = count + 1;
            return count;
        } finally {
            lock.unlock();
        }
    }
}