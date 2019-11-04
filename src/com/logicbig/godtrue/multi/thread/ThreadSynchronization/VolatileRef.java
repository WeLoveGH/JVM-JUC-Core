package com.logicbig.godtrue.multi.thread.ThreadSynchronization;

import java.util.concurrent.TimeUnit;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-25
 */
public class VolatileRef {

    private static volatile Data data = new Data(-1, -1);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 3; i++) {

            int a = i;
            int b = i;

            //writer
            Thread writerThread = new Thread(() -> {
                data.setA(a);
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                data.setB(b);
            });

            //reader
            Thread readerThread = new Thread(() -> {
                int x = data.getA();
                int y = data.getB();
                if (x != y) {
                    System.out.printf("a = %s, b = %s%n", x, y);
                }
            });

            writerThread.start();
            readerThread.start();

            writerThread.join();
            readerThread.join();
        }
        System.out.println("finished");
    }

    private static class Data {

        private int a;
        private int b;

        public Data(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public void setA(int a) {
            this.a = a;
        }

        public void setB(int b) {
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }
    }
}


/*

In case of volatile reference object,
it is ensured that the reference itself will be visible to other threads in timely manner but the same is not true for its member variables.

There is no guarantee that data contained within the object will be visible consistently if accessed individually

*/
