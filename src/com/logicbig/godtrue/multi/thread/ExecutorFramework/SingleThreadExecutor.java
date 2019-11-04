package com.logicbig.godtrue.multi.thread.ExecutorFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-25
 */
public class SingleThreadExecutor {

    public static void main (String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<?> future = executorService.submit(new Runnable() {
            @Override
            public void run () {
                System.out.println("task running");
            }
        });

        System.out.println(future);

        executorService.shutdown();
    }
}