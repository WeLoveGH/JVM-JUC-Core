package com.logicbig.godtrue.multi.thread.AsynchronousCommutationResult;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-28
 */
public class FutureNoResults {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService es = Executors.newSingleThreadExecutor();

        /**
         * 使用 Runnable 的任务没有返回值
         */
        Future<?> futureResult = es.submit(new Runnable() {
            @Override
            public void run() {
                //simulating long running task
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("callable submitted");

        //after doing other stuff
        System.out.println("getting result");

        Object o = futureResult.get();

        System.out.println(o);

        es.shutdown();
    }
}