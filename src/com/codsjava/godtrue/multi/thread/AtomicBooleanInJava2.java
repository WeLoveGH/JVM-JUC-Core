package com.codsjava.godtrue.multi.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description：非原子布尔类型示例
 * @author：qianyingjie1
 * @create：2019-10-22
 */
public class AtomicBooleanInJava2 {

    private static Boolean init = new Boolean(false);

    public static void init() {
        if(init==false){
            init=true;
            //非原子操作，可能出现两次
            System.out.println("initializing");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int c = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(c);
        for (int i = 0; i < c; i++) {
            executorService.execute(AtomicBooleanInJava2::init);
        }

        executorService.shutdown();

        executorService.awaitTermination(10, TimeUnit.MINUTES);
    }
}
