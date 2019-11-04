package com.logicbig.godtrue.multi.thread.Synchronizers;

import java.util.concurrent.Phaser;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-29
 */
public class PhaserExample {

    /**
     * 构建一个移位器
     */
    private static final Phaser phaser = new Phaser();

    public static void main(String[] args) throws InterruptedException {

        /**
         * 启动三个任务，依次延迟 0/500/1000 ms
         */
        startTask(0);
        startTask(500);
        //startTask(1000);
    }

    private static void startTask(long initialDelay) throws InterruptedException {
        /**
         * 当前线程休眠指定的时间
         */
        Thread.sleep(initialDelay);

        /**
         * 创建线程，并启动
         */
        new Thread(PhaserExample::taskRun).start();
    }

    private static void taskRun() {
        //registering this thread
        /**
         * 注册当前线程
         */
        phaser.register();

        /**
         * 打印当前线程及移位器的相关信息
         */
        print("after registering");

        for (int i = 1; i <= 2; i++) {

            try {
                //doing some work
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //the barrier point
            print("before arrive " + i);

            //current thread will wait for others to arrive
            phaser.arriveAndAwaitAdvance();

            print("after arrive " + i);
        }
    }

    private static void print(String msg) {
        System.out.printf("%-20s: %10s, registered=%s, arrived=%s, unarrived=%s phase=%s%n",
                msg,
                Thread.currentThread().getName(),
                phaser.getRegisteredParties(),
                phaser.getArrivedParties(),
                phaser.getUnarrivedParties(),
                phaser.getPhase()
        );
    }
}


/*


after registering   :   Thread-0, t=14:24:41.150, registered=1, arrived=0, unarrived=1 phase=0
after registering   :   Thread-1, t=14:24:41.607, registered=2, arrived=0, unarrived=2 phase=0
after registering   :   Thread-2, t=14:24:42.631, registered=3, arrived=0, unarrived=3 phase=0

before arrive 1     :   Thread-0, t=14:24:44.169, registered=3, arrived=0, unarrived=3 phase=0
before arrive 1     :   Thread-1, t=14:24:44.607, registered=3, arrived=1, unarrived=2 phase=0
before arrive 1     :   Thread-2, t=14:24:45.632, registered=3, arrived=2, unarrived=1 phase=0

after arrive 1      :   Thread-0, t=14:24:45.634, registered=3, arrived=0, unarrived=3 phase=1
after arrive 1      :   Thread-2, t=14:24:45.635, registered=3, arrived=0, unarrived=3 phase=1
after arrive 1      :   Thread-1, t=14:24:45.636, registered=3, arrived=0, unarrived=3 phase=1

before arrive 2     :   Thread-0, t=14:24:48.636, registered=3, arrived=0, unarrived=3 phase=1
before arrive 2     :   Thread-1, t=14:24:48.640, registered=3, arrived=1, unarrived=2 phase=1
before arrive 2     :   Thread-2, t=14:24:48.640, registered=3, arrived=1, unarrived=2 phase=1

after arrive 2      :   Thread-0, t=14:24:48.642, registered=3, arrived=0, unarrived=3 phase=2
after arrive 2      :   Thread-2, t=14:24:48.643, registered=3, arrived=0, unarrived=3 phase=2
after arrive 2      :   Thread-1, t=14:24:48.645, registered=3, arrived=0, unarrived=3 phase=2

Process finished with exit code 0

*/
