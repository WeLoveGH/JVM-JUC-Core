package com.logicbig.godtrue.multi.thread.AsynchronousCommutationResult;

import java.util.concurrent.*;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-28
 */
public class FutureIsDone {

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

        while(!futureResult.isDone()) {
            //do some other stuff
            Thread.sleep(10);
        }

        System.out.println("task done");

        //will not block
        Long result = futureResult.get();

        System.out.println(result);

        es.shutdown();
    }
}