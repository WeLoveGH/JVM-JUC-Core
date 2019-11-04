package com.codsjava.godtrue.multi.thread;

import java.util.concurrent.Exchanger;

/**
 * @description：Exchanger示例
 * @author：qianyingjie1
 * @create：2019-10-22
 */
public class ExchangerInJava {
    public static void main(String args[]) throws InterruptedException {
        /**
         * 创建交换器
         */
        Exchanger exchanger = new Exchanger();

        /**
         * 创建线程
         */
        ExchangerRunnable exchangerRunnable1 =
                new ExchangerRunnable(exchanger, "Object A");

        ExchangerRunnable exchangerRunnable2 =
                new ExchangerRunnable(exchanger, "Object B");

        System.out.println("exchange before ");
        System.out.println("exchangerRunnable1 "+exchangerRunnable1);
        System.out.println("exchangerRunnable2 "+exchangerRunnable2);

        /**
         * 启动线程
         */
        Thread threadA = new Thread(exchangerRunnable1);
        Thread threadB = new Thread(exchangerRunnable2);

        threadA.setName("Thread A ");
        threadB.setName("Thread B ");

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println("exchange after ");
        System.out.println("exchangerRunnable1 "+exchangerRunnable1);
        System.out.println("exchangerRunnable2 "+exchangerRunnable2);
    }
}

class ExchangerRunnable implements Runnable{
    /**
     * 声明交换器
     */
    Exchanger exchanger = null;

    /**
     * 声明待交换的对象
     */
    Object    object    = null;

    /**
     * 线程构造器
     * @param exchanger
     * @param object
     */
    public ExchangerRunnable(Exchanger exchanger, Object object) {
        this.exchanger = exchanger;
        this.object = object;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExchangerRunnable{");
        sb.append("exchanger=").append(exchanger);
        sb.append(", object=").append(object);
        sb.append('}');
        return sb.toString();
    }

    /**
     * 实现 run 方法
     */
    @Override
    public void run() {
        try {
            /**
             * 前一个对象
             */
            Object previous = this.object;

            /**
             * 通过交换器交换后，获取的对象
             */
            this.object = this.exchanger.exchange(this.object);

            System.out.println(
                    Thread.currentThread().getName() +
                            " exchanged " + previous + " for " + this.object
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


/*
The java.util.concurrent.Exchanger Class provides a sort of rendezvous point for two threads.
At this point the two threads can exchange their respective Objects with the each other.
*/
