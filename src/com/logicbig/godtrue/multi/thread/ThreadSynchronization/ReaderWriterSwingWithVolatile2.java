package com.logicbig.godtrue.multi.thread.ThreadSynchronization;

import javax.swing.*;
import java.awt.*;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-25
 */
public class ReaderWriterSwingWithVolatile2 {
    /**
     * 不加 volatile 关键字，不能保证可见性
     */
    static int c;

    public static void main(String[] args) {

        JFrame frame = createFrame();
        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 5));

        final ProgressUi writerProgressUi = new ProgressUi("Writer Thread");
        final ProgressUi readerProgressUi = new ProgressUi("Reader Thread");

        frame.add(writerProgressUi.getProgressComponent());
        frame.add(readerProgressUi.getProgressComponent());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Thread thread1 = new Thread(() -> {
            int temp = 0;
            while (true) {
                if (temp != c) {
                    temp = c;
                    readerProgressUi.update(temp);
                }
            }
        });

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

        void update(int c) {
            progressBar.setValue(c);
        }
    }

    private static JFrame createFrame() {
        JFrame frame = new JFrame("Visibility Demo without Volatile");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(400, 80));
        return frame;
    }
}