package com.logicbig.godtrue.multi.thread.ThreadBasics;

/**
 * @description：create thread by runnable
 * <p>
 * 两个线程交替打印 1~100 的奇偶数
 * <p>
 * 思路：
 * 1：创建两个线程
 * 2：一个负责打印偶数，一个负责打印奇数
 * 3：通过 synchronized 同步器控制两个线程的并发
 * 4：通过 sleep 方法控制线程的延迟
 * 5：通过 wait 和 notify 或者 notifyAll 方法来控制线程间的协作，一个线程打印完后就等待，然后唤醒另外一个线程打印，同样打印完后就等待
 * 这个性能性能相对更好
 * 6：通过 while 和 ++ 控制变量的自增和阈值
 * <p>
 * 7：仔细思考一下这个程序还有什么优化空间？
 *
 *
 * 通过实验证明，通过循环判断是否是奇偶数，然后决定是否加一打印的方式性能确实比较差，无论是使用那种类型的锁，性能明显都比较差
 *
 *
 * @author：qianyingjie1
 * @create：2019-10-23
 */
public class PrintOddEvenNumber21 {

    private static int count = 0;

    private static final Object LOCK = new Object();

    private static final int MAX_VALUE = 100;

    private static final int SLEEP_TIMEOUT = 10;

    public static void main(String[] args) {
        PrintOddEvenRunnable printOddEvenRunnable = new PrintOddEvenRunnable(count);

        Thread odd = new Thread(printOddEvenRunnable, "偶数");
        Thread even = new Thread(printOddEvenRunnable, "奇数");

        odd.start();

        try {
            Thread.sleep(SLEEP_TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        even.start();

    }

    static class PrintOddEvenRunnable implements Runnable {
        private int count;

        public PrintOddEvenRunnable(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            while (count <= MAX_VALUE) {
                synchronized (LOCK) {
                    LOCK.notify();
                    //LOCK.notifyAll();

                    System.out.println(Thread.currentThread().getName() + " " + count++);

                    try {
                        LOCK.wait(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}


