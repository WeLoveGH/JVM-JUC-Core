package com.codsjava.godtrue.multi.thread;

/**
 * @description：饿锁
 * @author：qianyingjie1
 * @create：2019-10-18
 */
public class StarvationExample implements Runnable{
    private final Object resource;
    private final String message;
    private final boolean fair;


    /**
     *
     * Starvation is a situation when a thread is in waiting state from long period
     *
     * because it not getting access of shared resources or because higher priority threads are coming
     *
     * 活锁
     *
     */


    public static void main(String[] args)
    {
        boolean fair = false;
        if (args != null && args.length >= 1 && args[0].equals("fair")) {
            fair = true;
        }

        // get the number of available CPUs, do twice as much threads.
        final int cpus = Runtime.getRuntime().availableProcessors();
        System.out.println("" + cpus + " available CPUs found");
        final int runners = cpus * 2;
        System.out.println("starting " + runners + " runners");

        final Object resource = new Object();

        // create sample runners and start them
        for (int i = 1; i <= runners; i++) {
            (new Thread(new StarvationExample(resource, String.valueOf(i), fair))).start();
        }

        // suspend main thread
        synchronized (StarvationExample.class) {
            try {
                StarvationExample.class.wait();
            } catch (InterruptedException ignored) {
            }
        }
    }

    public StarvationExample(Object resource, String message, boolean fair)
    {
        this.resource = resource;
        this.message = message;
        this.fair = fair;
    }

    @Override
    public void run()
    {
        synchronized (this) {
            for (;;) {
                synchronized (resource) {
                    print(message);
                    try {
                        (fair ? resource : this).wait(100);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        }
    }

    private static void print(String str)
    {
        synchronized (System.out) {
            System.out.print(str);
            System.out.flush();
        }
    }
}
