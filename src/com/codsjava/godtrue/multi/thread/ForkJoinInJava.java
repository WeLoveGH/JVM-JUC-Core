package com.codsjava.godtrue.multi.thread;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @description：forkjoin框架示例
 * @author：qianyingjie1
 * @create：2019-10-22
 */
public class ForkJoinInJava {

    public static void main(String[] args) {

        /**
         * 构建问题对象
         */
        Problem test = new Problem();

        /**
         * 获取可用的处理器核数，用于创建对应个数的线程
         */
        //Check the number of available processors
        int nThreads = Runtime.getRuntime().availableProcessors();
        System.out.println(nThreads);

        /**
         * 构建求解器对象
         */
        Solver solver = new Solver(test.getList());

        /**
         * 构建 forkJoin 线程池
         */
        ForkJoinPool pool = new ForkJoinPool(nThreads);

        /**
         * 执行解决方案
         */
        long startTime = System.nanoTime();
        pool.invoke(solver);
        long endTime = System.nanoTime();
        /**
         * 获取解决方案的结果
         */
        long result = solver.getResult();
        System.out.println("Done. Result: " + result + " cost time : "+(endTime-startTime));

        /**
         * 通过循环遍历的方式，计算数组的和，主要是为了验证是否和通过使用 forkJoin 框架计算的一致
         *
         * 通过简单的时间计算，发现还是 for 速度快一些
         *
         * 一百万
         * 一千万
         * 一个亿都是如此
         *
         */
        long startTime2 = System.nanoTime();
        long sum = 0;
        for (int i = 0; i < test.getList().length; i++) {
            sum += test.getList()[i];
        }
        long endTime2 = System.nanoTime();
        System.out.println("Done. sum:    " + sum + " cost time : "+(endTime2-startTime2));
    }
}

class Problem {
    /**
     * 1：构建一个一百万大小的数组
     * 2：放入一百万个随机数
     */
    private final int[] list = new int[1000000];
    public Problem() {
        Random generator = new Random(19580427);
        for (int i = 0; i < list.length; i++) {
            list[i] = generator.nextInt(250000);
        }
    }

    public int[] getList() {
        return list;
    }
}

class Solver extends RecursiveAction {
    /**
     * 声明存储任务数组
     */
    private int[] list;

    /**
     * 声明村塾计算结果的变量
     */
    public long result;

    public Solver(int[] array) {
        this.list = array;
    }

    /**
     * 复写计算方法
     */
    @Override
    protected void compute() {

        /**
         * 1：如果数组长度为一，则计算结果就只这个数组元素的值
         * 2：如果数组长度大于一，则将数组从中间一分为二，然后构建两个求解器
         * 3：求解器一调用 fork 方法
         * 4：求解器二调用 compute 方法——递归调用
         * 5：求解器一等待求解器二计算完成一起返回
         * 6：最终的计算结果就是求解器一和求解器二的结果之和
         */

        if (list.length == 1) {
            result = list[0];
        } else {
            int midpoint = list.length / 2;
            int[] l1 = Arrays.copyOfRange(list, 0, midpoint);
            int[] l2 = Arrays.copyOfRange(list, midpoint, list.length);
            Solver s1 = new Solver(l1);
            Solver s2 = new Solver(l2);
            s1.fork();
            s2.compute();
            s1.join();
            result = s1.result + s2.result;
        }
    }

    public long getResult() {
        return result;
    }
}


/*

The ForkJoinPool was introduced in java 7.
It works on fork and join principle.
In first step (fork) the task splits itself into smaller subtask which will be executed concurrently.
In second step (join) when subtask completes its execution, the task will merge all results into one result.

*/
