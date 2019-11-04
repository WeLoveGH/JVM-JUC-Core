package com.codsjava.godtrue.multi.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description：callable and future
 * @author：qianyingjie1
 * @create：2019-10-22
 */
public class CallableAndFutureInJava {

    public static void main(String args[]){

        /**
         * 创建 3 个线程的固定线程池
         */
        ExecutorService es = Executors.newFixedThreadPool(3);

        /**
         * 提交 5 个 callable 任务
         */
        Future<String> f1 = es.submit(new MyCallable("callable"));
        Future<String> f2 = es.submit(new MyCallable("future"));
        Future<String> f3 = es.submit(new MyCallable("codesjava"));
        Future<String> f4 = es.submit(new MyCallable("codesjava executor service"));
        Future<String> f5 = es.submit(new MyCallable("executors classes"));

        /**
         * 打印任务执行的结果
         */

        try {
            System.out.println("1. " + f1.get());
            System.out.println("2. " + f2.get());
            System.out.println("3. " + f3.get());
            if(f4.isDone()){
                System.out.println("4. " + f4.get());
            }else{
                System.out.println("waiting");
            }
            System.out.println("5. " + f5.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 关闭线程池
         */
        es.shutdown();
    }
}

/**
 * callable任务器
 */
class MyCallable implements Callable<String> {
    /**
     * 任务名参数
     */
    String str;

    /**
     * 任务构造器
     * @param str
     */
    MyCallable(String str){
        this.str = str;
    }

    /**
     * 复写 call 方法
     * @return
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " In call method of Callable " + str);
        StringBuffer sb = new StringBuffer();
        return (sb.append("Length of string ").append(str).append(" is ").
                append(str.length())).toString();

    }

}

/*


The Callable interface available in java.util.concurrent package.
It contains one method call() which returns the Future object.
Return value can be retrieved after termination with get.
The Future object is used to check the status of a Callable.
Result can be retrieved from the Callable once the thread is done.

*/
