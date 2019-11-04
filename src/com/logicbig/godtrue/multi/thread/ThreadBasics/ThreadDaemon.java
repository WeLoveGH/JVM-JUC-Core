package com.logicbig.godtrue.multi.thread.ThreadBasics;

import java.util.ArrayList;
import java.util.List;

/**
 * @description：thread daemon
 * @author：qianyingjie1
 * @create：2019-10-24
 */
public class ThreadDaemon {
    static List<String> list = new ArrayList<>();

    public static void main (String[] args) throws InterruptedException {

        MemoryWatcherThread.start();
        for (int i = 0; i < 100000; i++) {
            String str = new String("str" + i);
            list.add(str);
        }

        System.out.println("end of main method");
        //System.exit(0);
    }
}

class MemoryWatcherThread implements Runnable {
    public static void start () {
        Thread thread = new Thread(new MemoryWatcherThread());
        thread.setPriority(Thread.MAX_PRIORITY);
        //thread.setDaemon(true);
        thread.setDaemon(false);
        thread.start();
    }

    @Override
    public void run () {
        while (true) {
            System.out.println(Thread.currentThread().getName()+" Memory used :" + getMemoryUsed() + " MB");
        }
    }

    private long getMemoryUsed () {
        return (Runtime.getRuntime()
                .totalMemory() - Runtime.getRuntime()
                .freeMemory()) / 1024 / 1024;
    }

}
/*

Difference between Daemon and Non Daemon(User Threads) is that: JVM waits for non-daemon threads to finish executing before terminate the main program.
On the other hand, JVM doesn't wait for daemon thread to finish. JVM can exist even if daemon threads are still running.

Using System.exit() at the end of main method, will terminate JVM even for non-daemon thread.

*/
