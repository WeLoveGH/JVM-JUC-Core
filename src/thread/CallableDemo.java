package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask, "AA").start();
        int result01 = 100;

        // 获取任务执行的返回值
        int result02 = futureTask.get();
        System.out.println("result=" + (result01 + result02));
    }
}

/**
 * Callable接口也能构造可执行的任务，他和Runnable接口类似，只是Callable执行完任务是有返回值的
 */
class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("callable come in ...");
        return 1024;
    }
}
