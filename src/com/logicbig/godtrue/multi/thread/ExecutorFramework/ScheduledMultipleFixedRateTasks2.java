package com.logicbig.godtrue.multi.thread.ExecutorFramework;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-28
 */
public class ScheduledMultipleFixedRateTasks2 {

    public static void main (String[] args) throws InterruptedException {

        // equivalent to  Executors.newScheduledThreadPool(2)

        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(2);

        service.scheduleAtFixedRate(new MyTask("Task 1"), 500, 1000, TimeUnit.MILLISECONDS);
        service.scheduleAtFixedRate(new MyTask("Task 2"), 500, 1000, TimeUnit.MILLISECONDS);

        PoolUtil.showPoolDetails((ThreadPoolExecutor) service, "For each task" +
                " initial delay: 500 ms," +
                " delay period: 1000 ms," +
                " repeat policy: fixed-rate");

        TimeUnit.SECONDS.sleep(10);
        service.shutdown();
    }
}