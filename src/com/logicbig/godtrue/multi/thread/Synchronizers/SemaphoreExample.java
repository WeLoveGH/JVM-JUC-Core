package com.logicbig.godtrue.multi.thread.Synchronizers;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-28
 *
 *
 * Semaphores are often used to restrict the number of threads that can run at the same time.
 * These threads may be working on a shared resource or they may be working independently to achieve similar tasks.
 *
    Semaphore internally uses a synchronized queue (AbstractQueuedSynchronizer), that means it takes care of thread safety.
    Also release() method establishes happen-before relation with respect to the subsequent acquire() call.

   信号量，主要用于处理工作线程数，多余资源数的情况，工作线程怎么配合的运行，比如：只有两个车位，六辆车怎么停，这类问题


   最直观的例子就是火车厕所的例子，一节火车，可以容纳100人，不过只有一个厕所，大家上厕所，怎么办
   1：如果当前厕所没人使用，则大家看谁快看谁离得近，谁先抢到谁先用
   2：当前厕所有人了，灯就会变红，所有其他想上厕所的人就必须在厕所门口排队等待了
   3：只有一个人从厕所里出来了，下一个排在厕所对头的人才能进入厕所
   4：厕所的灯，就是一种信号量，绿灯代表没人使用，红灯代表有人使用，
      当没人使用时，大家需要争抢使用，谁抢到谁用
      当有人使用时，大家需要排队等待，知道有人使用完毕退出，然后大家再次争抢

 *
 *
 *
 *
 *
 */
public class SemaphoreExample {

    /**
     * 工作线程个数 6 个
     */
    private static final int WORKER_COUNT = 6;

    /**
     * 凭证个数 2 个
     */
    private static final int PERMITS = 2;

    public static void main(String[] args) throws Exception {

        /**
         * 初始化面板
         */
        List<WorkerPanel> workers = initPanels();

        Collections.shuffle(workers);

        /**
         * 构建信号量
         */
        Semaphore semaphore = new Semaphore(PERMITS);

        /**
         * 构建线程池——固定线程个数 6 个
         */
        ExecutorService es = Executors.newFixedThreadPool(WORKER_COUNT);

        while (true) {
            for (WorkerPanel worker : workers) {

                //it will block until a permit is available
                /**
                 * The method Semaphore#acquire() increases the underlying count.
                 * If the count is more than the max value, this method blocks until the current value of count becomes less than or equal to max value
                 *
                 * 获取凭证的方法，会增加计数器的个数，如果计算器的个数大于最大值，则会阻塞方法，知道计数器的个数小于或者等于最大值
                 *
                 */
                semaphore.acquire();

                es.execute(() -> {
                    /**
                     * 工作线程工作，像雷达一样不断你的扫描
                     */
                    worker.work();

                    /**
                     * The method Semaphore#release() decreases the permit count, which may potentially release a blocking acquire() call.
                     *
                     * 释放凭证的方法，会减少计数器的个数，可能会唤醒阻塞的线程
                     *
                     */
                    semaphore.release();
                });
            }
        }
    }

    /**
     *
     * 初始化面板
     *
     * 1：创建一个窗体
     * 2：设置窗体的样式
     * 3：在窗体上添加六个面板
     * 4：这是面的样式
     *
     * @return
     */
    private static List<WorkerPanel> initPanels() {
        JFrame frame = new JFrame("Permits: " + PERMITS);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 200);
        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        List<WorkerPanel> panels = new ArrayList<WorkerPanel>();
        for (int i = 0; i < WORKER_COUNT; i++) {
            WorkerPanel wp = new WorkerPanel();
            frame.add(wp);
            panels.add(wp);
        }

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return panels;
    }
}

class WorkerPanel extends JComponent {

    private static final int PANEL_SIZE = 60;
    private int angle = 0;
    private boolean active;

    public WorkerPanel() {
        setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
    }

    public void work() {
        active = true;
        int cycle = ThreadLocalRandom.current().nextInt(100, 200);
        for (int i = 0; i < cycle; i++) {
            angle += 5;
            if (angle >= 360) {
                angle = 0;
            }
            repaint();
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
            }
        }
        active = false;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(active ? Color.BLUE : Color.LIGHT_GRAY);
        g.drawArc(0, 0, PANEL_SIZE, PANEL_SIZE, 0, 360);
        g.fillArc(0, 0, PANEL_SIZE, PANEL_SIZE, angle, 30);
    }
}