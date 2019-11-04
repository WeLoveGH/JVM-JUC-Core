package com.logicbig.godtrue.multi.thread.ThreadBasics;

import java.util.concurrent.TimeUnit;

/**
 * @description：thread interrupt
 * @author：qianyingjie1
 * @create：2019-10-24
 */
public class ThreadInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Task task1 = new Task();
        Thread thread1 = new Thread(task1);
        thread1.start();

        while (true) {
            if (Math.random() > 0.5) {
                thread1.interrupt();
                break;
            }
            TimeUnit.MILLISECONDS.sleep(1);
        }

    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            int c = 0;

            while (true) {

                System.out.println("task running .. " + ++c);

                if (Thread.currentThread()
                        .isInterrupted()) {
                    System.out.println("interrupted flag=true");
                    terminate();
                    return;
                }

                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("interrupted exception ");
                    terminate();
                    return;
                }
            }

        }

        private void terminate() {
            System.out.println("Terminating task");
        }
    }
}


/*


An interrupt is the indication to a thread that it should stop what it is doing and do something else.

Interrupts are operating system level mechanism to signal threads about some particular event.

*/
