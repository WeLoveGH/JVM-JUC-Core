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
public class ScheduledSingleFixedRateTask {

    public static void main (String[] args) throws InterruptedException {

        System.out.println("Thread pool size: 1.\nTask submitted: 1" +
                "\n\nEach tasks have:" +
                "\nSchedule policy: fixed-rate" +
                "\nInitial delay: 500\ndelay period: 1000:\n");


        // equivalent to Executors.newSingleThreadScheduledExecutor()

        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1);

        service.scheduleAtFixedRate(new MyTask("Task 1"), 500, 1000, TimeUnit.MILLISECONDS);

        PoolUtil.showPoolDetails((ThreadPoolExecutor) service, "For each task" +
                " initial delay: 500 ms," +
                " delay period: 1000 ms," +
                " repeat policy: fixed-rate");

        TimeUnit.SECONDS.sleep(10);
        service.shutdown();
    }
}