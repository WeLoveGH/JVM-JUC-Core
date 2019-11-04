package com.codsjava.godtrue.multi.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description：非原子长整型示例
 * @author：qianyingjie1
 * @create：2019-10-22
 */
public class AtomicLongInJava2 {

    private static Long result = new Long(0);

    public static void main(String[] args) throws InterruptedException {

        /**
         * 1：循环十次
         * 2：通过一个五十固定线程的线程池，来进行操作，分别开启二十个线程，对循环中的整数进行指数操作，然后进行累加
         * 3：关闭线程池
         * 4：等待线程执行完毕
         * 5：打印执行结果
         * 6：通过观察打印的日志发现累计数值，只有部分符合预期都等于 2097150，其他的小于 2097150
         */

        for (int i = 0; i < 10; i++) {
            result=0L;
            ExecutorService es = Executors.newFixedThreadPool(50);
            for (int j = 1; j <= 20; j++) {
                int finalI = j;
                es.execute(() -> {
                    result+=(long) Math.pow(2, finalI);
                });
            }

            es.shutdown();

            es.awaitTermination(10, TimeUnit.MINUTES);

            System.out.println(result);
        }
    }
}
