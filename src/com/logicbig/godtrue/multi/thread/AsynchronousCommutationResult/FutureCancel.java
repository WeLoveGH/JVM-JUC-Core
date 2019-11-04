package com.logicbig.godtrue.multi.thread.AsynchronousCommutationResult;

import java.util.concurrent.*;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-28
 */
public class FutureCancel {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService es = Executors.newSingleThreadExecutor();

        Future<Long> futureResult = es.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                //simulating long running task
                Thread.sleep(1000);
                System.out.println("returning");
                return ThreadLocalRandom.current().nextLong();
            }
        });

        System.out.println("callable submitted");

        //after doing other stuff
        System.out.println("canceling task");

        boolean c = futureResult.cancel(true);

        System.out.println(c);

        //following will throw java.util.concurrent.CancellationException
        //Long result = futureResult.get();

        es.shutdown();
    }
}