package com.codsjava.godtrue.multi.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description：非原子整数示例
 *
 * 这个示例主要是为了和原子整型做对比，通过实验证明非原子整型确实在多线程环境下容易出错
 *
 * @author：qianyingjie1
 * @create：2019-10-22
 */
public class AtomicIntegerInJava2 {

    private static Integer num = new Integer(0);

    public static void main(String[] args) throws InterruptedException {
        /**
         * 1：循环十次
         * 2：通过一个两个固定线程的线程池，来进行操作，分别开启两个线程，对一个整数进行1000次的累加操作
         * 3：关闭线程池
         * 4：等待线程执行完毕
         * 5：打印执行结果
         * 6：通过观察打印的日志发现累计数值确实存在，不符合预期的预期2000，存在小于2000的情况
         */
        for (int i = 0; i < 10; i++) {
            num=0;
            ExecutorService es = Executors.newFixedThreadPool(2);
            es.execute(() -> {
                for (int j = 0; j < 1000; j++) {
                    num+=1;
                }
            });
            es.execute(() -> {
                for (int j = 0; j < 1000; j++) {
                    num+=1;
                }
            });

            es.shutdown();
            es.awaitTermination(10, TimeUnit.MINUTES);
            System.out.println(Thread.currentThread().getName()+ " "+num);
        }
    }
}
