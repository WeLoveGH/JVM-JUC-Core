package com.codsjava.godtrue.multi.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @description：原子布尔类型示例
 * @author：qianyingjie1
 * @create：2019-10-22
 */
public class AtomicBooleanInJava {

    /**
     * 创建原子布尔类型
     */
    private static AtomicBoolean init = new AtomicBoolean();

    /**
     * 初始化方法
     */
    public static void init() {
        if (init.compareAndSet(false, true)) {
            System.out.println("initializing");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /**
         * 创建五个固定线程的线程池，并执行初始化的方法
         */
        int c = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(c);
        for (int i = 0; i < c; i++) {
            executorService.execute(AtomicBooleanInJava::init);
        }
        /**
         * 关闭线程池
         */
        executorService.shutdown();

        /**
         * 等待线程池中的线程执行完毕，最多等十分钟
         */
        executorService.awaitTermination(10, TimeUnit.MINUTES);
    }
}
/*

The java.util.concurrent.atomic.AtomicBoolean class provides a boolean variable which can be read and written atomically.

*/
