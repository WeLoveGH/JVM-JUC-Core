package com.logicbig.godtrue.multi.thread.ThreadBasics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description：thread join
 * @author：qianyingjie1
 * @create：2019-10-24
 */
public class ThreadJoin2 {
    public static void main(String[] args) {

        /**
         * 构建一个列表，用于计算对应的阶乘
         */
        final List<Integer> integers = Arrays.asList(10, 12, 13, 14, 15, 20);

        //创建一个线程，作用类似主线程，在这个线程中会在创建其他的线程进行阶乘的计算
        new Thread(() -> {

            /**
             * 构建计算阶乘的线程列表
             */
            List<FactorialCalculator> threads = new ArrayList<>();

            /**
             * 循环构建计算阶乘的线程，且启动线程
             */
            for (Integer integer : integers) {
                /**
                 * 构建阶乘计算的线程，放入列表，且启动
                 */
                FactorialCalculator calc = new FactorialCalculator(integer);
                threads.add(calc);
                calc.start();
            }

            /**
             * 遍历列表，暂停执行 parent thread 的后续逻辑，直到所有的计算阶乘的线程执行完毕
             */
            for (FactorialCalculator calc : threads) {
                try {
                    calc.join();
                    System.out.println(calc.getNumber() + "! = "
                            + calc.getFactorial());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"parent thread").start();
    }

    private static class FactorialCalculator extends Thread {

        private final int number;
        private BigDecimal factorial;

        FactorialCalculator(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            factorial = calculateFactorial(number);
        }

        /**
         *
         * 利用 for 循环 计算一个整数的阶乘
         *
         * @param number
         * @return
         */
        private static BigDecimal calculateFactorial(int number) {
            BigDecimal factorial = BigDecimal.ONE;
            for (int i = 1; i <= number; i++) {
                factorial = factorial.multiply(new BigDecimal(i));
            }
            return factorial;
        }

        public BigDecimal getFactorial() {
            return factorial;
        }

        public int getNumber() {
            return number;
        }
    }
}

/*

halts the calling thread execution until the thread represented by this instance terminates

*/
