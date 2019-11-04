package com.logicbig.godtrue.multi.thread.AsynchronousCommutationResult;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-28
 */
public class FutureTask {

/*    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() {
                try {
                    //simulating long running task
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("task finished");
                return "The result";
            }
        });

        //jdk11
        //new Thread(futureTask).start();

        System.out.println("Thread started");

        //String s = futureTask.get();

        //System.out.println(s);
    }*/
}