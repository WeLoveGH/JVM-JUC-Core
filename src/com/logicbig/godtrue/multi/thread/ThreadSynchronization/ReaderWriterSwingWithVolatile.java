package com.logicbig.godtrue.multi.thread.ThreadSynchronization;

import javax.swing.*;
import java.awt.*;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-25
 */
public class ReaderWriterSwingWithVolatile {
    /**
     * 使用 volatile 关键字保证共享变量的可见性
     */
    static volatile int c;

    public static void main(String[] args) {

        /**
         * 构建窗体
         */
        JFrame frame = createFrame();
        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 5));

        /**
         * 构建两个进度条
         */
        final ProgressUi writerProgressUi = new ProgressUi("Writer Thread");
        final ProgressUi readerProgressUi = new ProgressUi("Reader Thread");

        frame.add(writerProgressUi.getProgressComponent());
        frame.add(readerProgressUi.getProgressComponent());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        /**
         * 构建读线程，不断的去读共享变量，只要和上一次的值不一样就更新他，并且体现在进度条上
         */
        Thread thread1 = new Thread(() -> {
            int temp = 0;
            while (true) {
                if (temp != c) {
                    temp = c;
                    readerProgressUi.update(temp);
                }
            }
        });

        /**
         * 构建写线程，循环一百次，修改共享变量，并且显示在写线程的进度条上
         */
        Thread thread2 = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                c++;
                writerProgressUi.update(c);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (i == 100) {
                    i = 1;
                    c = 0;
                }
            }
        });

        /**
         * 启动读写两个线程
         */
        thread1.start();
        thread2.start();
    }

    private static class ProgressUi {
        private JProgressBar progressBar = new JProgressBar();

        ProgressUi(String name) {
            progressBar.setString(name);
            progressBar.setStringPainted(true);
        }

        JComponent getProgressComponent() {
            return progressBar;
        }

        /**
         * 调整进度条的值，好动态的展示进度的变化
         * @param c
         */
        void update(int c) {
            progressBar.setValue(c);
        }
    }

    private static JFrame createFrame() {
        JFrame frame = new JFrame("Visibility Demo with Volatile");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(400, 80));
        return frame;
    }
}