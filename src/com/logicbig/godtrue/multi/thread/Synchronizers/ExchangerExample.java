package com.logicbig.godtrue.multi.thread.Synchronizers;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-29
 */
public class ExchangerExample {

    public static void main(String... args) throws InterruptedException {

        Exchanger<MyExchangeData> exchanger = new Exchanger<MyExchangeData>();

        ExecutorService es = Executors.newFixedThreadPool(2);
        //party1
        es.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    for (int i = 1; i <= 5; i++) {

                        System.out.println("-- party1 next --");

                        MyExchangeData data = new MyExchangeData("msg from party1 " + i);

                        System.out.println("party1 calling exchange() with data: "+data);

                        MyExchangeData exchange = exchanger.exchange(data);

                        System.out.println("party1 exchange() returned and received: " + exchange);

                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        });

        Thread.sleep(1000);
        //party2
        es.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 1; i <= 5; i++) {

                        System.out.println("-- party2 next --");

                        MyExchangeData data = new MyExchangeData("msg from party2 " + i);

                        System.out.println("party2 calling exchange() with data: "+data);

                        MyExchangeData exchange = exchanger.exchange(data);

                        System.out.println("party2 exchange() returned and received: " + exchange);

                        Thread.sleep(1000);
                    }

                    es.shutdown();

                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        });
    }

    private static class MyExchangeData {

        private String msg;

        public MyExchangeData(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "MyExchangeData{" +
                    "msg='" + msg + '\'' +
                    '}';
        }
    }
}

/*

Exchanger is a synchronization point at which threads can pair and swap elements within pairs.
An Exchanger may be viewed as a bidirectional form of a SynchronousQueue.
Exchanger#exchange(data) waits for another thread to also invoke exchange(data).
It is similar to Queue#poll() method but is synchronized and is bidirectional.
This method is a way to transfer the given object between threads.

这个类的作用，主要是在多个线程之间交换对象数据

*/
