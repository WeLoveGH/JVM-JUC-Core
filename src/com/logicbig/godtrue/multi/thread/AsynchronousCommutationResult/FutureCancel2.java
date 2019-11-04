package com.logicbig.godtrue.multi.thread.AsynchronousCommutationResult;

import java.util.concurrent.*;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-28
 */
public class FutureCancel2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService es = Executors.newSingleThreadExecutor();

        Future<Long> futureResult = es.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                System.out.println("task started and running");
                Thread.sleep(1000);
                System.out.println("returning result");
                return ThreadLocalRandom.current().nextLong();
            }
        });

        Thread.sleep(100);

        System.out.println("callable submitted");

        //after doing other stuff
        System.out.println("canceling task");

        boolean c = futureResult.cancel(false);

        System.out.println(c);

        //following will still throw java.util.concurrent.CancellationException
        //Long result = futureResult.get();

        es.shutdown();
    }
}