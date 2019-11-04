package com.codsjava.godtrue.multi.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description：原子整数示例
 * @author：qianyingjie1
 * @create：2019-10-22
 */
public class AtomicIntegerInJava {

    private static AtomicInteger num = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        /**
         * 1：循环十次
         * 2：通过一个两个固定线程的线程池，来进行操作，分别开启两个线程，对一个整数进行1000次的累加操作
         * 3：关闭线程池
         * 4：等待线程执行完毕
         * 5：打印执行结果
         * 6：通过观察打印的日志发现累计数值全部符合预期都等于2000
         */

        for (int i = 0; i < 10; i++) {
            num.set(0);
            ExecutorService es = Executors.newFixedThreadPool(2);
            es.execute(() -> {
                for (int j = 0; j < 1000; j++) {
                    num.incrementAndGet();
                }
            });
            es.execute(() -> {
                for (int j = 0; j < 1000; j++) {
                    num.incrementAndGet();
                }
            });

            es.shutdown();
            es.awaitTermination(10, TimeUnit.MINUTES);
            System.out.println(Thread.currentThread().getName()+ " "+num.get());
        }
    }
}

/*

The java.util.concurrent.atomic.AtomicInteger class provides an int variable which can be read and written atomically.

*/
