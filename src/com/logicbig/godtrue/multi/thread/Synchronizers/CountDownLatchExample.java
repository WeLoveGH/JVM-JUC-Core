package com.logicbig.godtrue.multi.thread.Synchronizers;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CountDownLatch;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-29
 *
 *
 *
 *
 *
 *
In Java, a CountDownLatch allows one or more threads to wait until a set of operations being performed in other threads completes.

A CountDownLatch is initialized with a given count.

The method CountDownLatch#countDown() decreases the count by one.

The method CountDownLatch#await() blocks until the underlying count becomes zero.

This can be useful for multiple purposes, for example a number of threads can be paused for other threads to reach to a certain point before progressing.

CountDownLatch is synchronized internally, so it can be used by multiple threads.
Also actions in a thread before calling countDown() establish happen-before relation with the actions following a successful return from a corresponding await() in another thread.
 *
 *
 *
 * 计数门闩计数器的使用场景类似，一群同学去看电影，约定好在学校门口等着，知道所有小伙伴都到了之后才继续出发
 *
 * 通过减法计数
 *
 *
 *
 *
 *
 *
 */
public class CountDownLatchExample {

    /**
     * 门闩的个数为 4
     */
    private static final int LATCH_COUNT = 4;

    public static void main(String[] args) {
        /**
         * 构建倒数门闩计数器
         */
        CountDownLatch latch = new CountDownLatch(LATCH_COUNT);

        /**
         * 构建窗口，设置窗口
         */
        JFrame frame = createFrame();
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));

        /**
         * 构建四个进度条，并且将他们放置到窗体上，四个进度条就是四个线程，且启动
         */
        for (int i = 1; i <= LATCH_COUNT; i++) {
            ProgressThread progressThread = new ProgressThread(latch, i * 10);
            frame.add(progressThread.getProgressComponent());
            progressThread.start();
        }

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JFrame createFrame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("CountDownLatch Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(ProgressThread.PROGRESS_WIDTH, 170));
        return frame;
    }
}

class ProgressThread extends Thread {
    public static final int PROGRESS_WIDTH = 350;
    private static final int CATCH_UP_COUNT = 75;
    private final JProgressBar progressBar;
    private final CountDownLatch latch;
    private final int slowness;

    public ProgressThread(CountDownLatch latch, int slowness) {
        this.latch = latch;
        this.slowness = slowness;
        progressBar = new JProgressBar();
        progressBar.setPreferredSize(
                new Dimension(PROGRESS_WIDTH - 30, 25));
        progressBar.setStringPainted(true);
    }

    JComponent getProgressComponent() {
        return progressBar;
    }

    /**
     * 复写线程类的 run 方法
     *
     * 1：不断的增加进度条的进度，直到进度为 100
     * 2：不同的进度条通过不同的休眠时间来控制，刚开始展示的进度，
     *    当所有的进度条都执行到 75% 时，四个线程的休眠时间就是一样的了，都是 100 ms，否则四个线程的休眠时间依次是 10/20/30/40 ms
     */
    @Override
    public void run() {

        int c = 0;
        while (true) {
            progressBar.setValue(++c);
            if (c > 100) {
                System.out.println(Thread.currentThread().getName()+"，c:"+c);
                break;
            }

            try {
                Thread.sleep(c < CATCH_UP_COUNT ? slowness : 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (c == CATCH_UP_COUNT) {

                System.out.println(Thread.currentThread().getName()+" 当前进度为："+c+" 调用countDown之前"+latch);

                //decrease the count
                //The method CountDownLatch#countDown() decreases the count by one.
                // 使倒数门闩计时器，计数减一
                latch.countDown();

                System.out.println(Thread.currentThread().getName()+" 当前进度为："+c+" 调用countDown之后"+latch);

                try {
                    //wait until count = 0
                    //The method CountDownLatch#await() blocks until the underlying count becomes zero.
                    //阻塞当前执行的线程，知道倒数门闩计数器的计数值为0

                    System.out.println(Thread.currentThread().getName()+" 当前进度为："+c+" 调用await之前"+latch);

                    latch.await();

                    System.out.println(Thread.currentThread().getName()+" 当前进度为："+c+" 调用await之后"+latch);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(Thread.currentThread().getName()+"  end");
    }
}


/*

As seen in the output, all four threads wait at 75% for the CountDownLatch to reach a value of zero (from initial value of 4) before progressing further.

The CountDownLatch logic from above example can be used with some useful multiple tasks which want to submit their individual work at the catch-up point.

*/


/*

从打印的日志上可以观察到：
1：倒数门闩计数器的计数值，每调用一次 contDown 方法，计数值都会减一
2：调用 await 方法会阻塞当前正在运行的线程，直到倒数门闩的计数器的计数值减为 0 为止，被阻塞的线程才开始运行
3：当计数器的计数值为 0 时，被阻塞的线程会被唤醒，不过谁先执行谁后执行是非公平的，是随机的

Thread-1 当前进度为：75 调用countDown之前java.util.concurrent.CountDownLatch@1b1138b8[Count = 4]
Thread-1 当前进度为：75 调用countDown之后java.util.concurrent.CountDownLatch@1b1138b8[Count = 3]

Thread-1 当前进度为：75 调用await之前java.util.concurrent.CountDownLatch@1b1138b8[Count = 3]


Thread-2 当前进度为：75 调用countDown之前java.util.concurrent.CountDownLatch@1b1138b8[Count = 3]
Thread-2 当前进度为：75 调用countDown之后java.util.concurrent.CountDownLatch@1b1138b8[Count = 2]

Thread-2 当前进度为：75 调用await之前java.util.concurrent.CountDownLatch@1b1138b8[Count = 2]


Thread-3 当前进度为：75 调用countDown之前java.util.concurrent.CountDownLatch@1b1138b8[Count = 2]
Thread-3 当前进度为：75 调用countDown之后java.util.concurrent.CountDownLatch@1b1138b8[Count = 1]

Thread-3 当前进度为：75 调用await之前java.util.concurrent.CountDownLatch@1b1138b8[Count = 1]


Thread-4 当前进度为：75 调用countDown之前java.util.concurrent.CountDownLatch@1b1138b8[Count = 1]
Thread-4 当前进度为：75 调用countDown之后java.util.concurrent.CountDownLatch@1b1138b8[Count = 0]

Thread-4 当前进度为：75 调用await之前java.util.concurrent.CountDownLatch@1b1138b8[Count = 0]


Thread-4 当前进度为：75 调用await之后java.util.concurrent.CountDownLatch@1b1138b8[Count = 0]
Thread-1 当前进度为：75 调用await之后java.util.concurrent.CountDownLatch@1b1138b8[Count = 0]
Thread-2 当前进度为：75 调用await之后java.util.concurrent.CountDownLatch@1b1138b8[Count = 0]
Thread-3 当前进度为：75 调用await之后java.util.concurrent.CountDownLatch@1b1138b8[Count = 0]


*/
