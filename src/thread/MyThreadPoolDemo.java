package thread;

import java.util.concurrent.*;

/**
 * 第四种使用Java多线程的方式，线程池
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //System.out.println("Fixed Thread Pool");
        // 固定线程个数的线程池
        //fixedThreadPool();
        //System.out.println("Single Thread Pool");
        // 只有一个线程的线程池
        //singleThreadPool();
        //System.out.println("Cached Thread Pool");
        // 弹性线程个数的线程池
        //cachedThreadPool();
        //System.out.println("Custom Thread Pool");
        // 自定义线程池
        customThreadPool();
    }

    private static void customThreadPool() {
        ExecutorService threadPool=
                new ThreadPoolExecutor(2,
                        5,
                        1L,
                        TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(3),
                        Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.AbortPolicy()
                );
        try {
            for (int i = 0; i < 9; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void cachedThreadPool() {
        //不定量线程
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 9; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void singleThreadPool() {
        //一池1个线程
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        try {
            for (int i = 0; i < 9; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void fixedThreadPool() {
        //一池5个线程
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //一般常用try-catch-finally
        //模拟10个用户来办理业务，每个用户就是一个线程
        try {
            for (int i = 0; i < 9; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}



    /**
     * Creates a new {@code ThreadPoolExecutor} with the given initial
     * parameters.
     *
     * @param corePoolSize the number of threads to keep in the pool, even
     *        if they are idle, unless {@code allowCoreThreadTimeOut} is set
     * @param maximumPoolSize the maximum number of threads to allow in the
     *        pool
     * @param keepAliveTime when the number of threads is greater than
     *        the core, this is the maximum time that excess idle threads
     *        will wait for new tasks before terminating.
     * @param unit the time unit for the {@code keepAliveTime} argument
     * @param workQueue the queue to use for holding tasks before they are
     *        executed.  This queue will hold only the {@code Runnable}
     *        tasks submitted by the {@code execute} method.
     * @param threadFactory the factory to use when the executor
     *        creates a new thread
     * @param handler the handler to use when execution is blocked
     *        because the thread bounds and queue capacities are reached
     * @throws IllegalArgumentException if one of the following holds:<br>
     *         {@code corePoolSize < 0}<br>
     *         {@code keepAliveTime < 0}<br>
     *         {@code maximumPoolSize <= 0}<br>
     *         {@code maximumPoolSize < corePoolSize}
     * @throws NullPointerException if {@code workQueue}
     *         or {@code threadFactory} or {@code handler} is null
     */
/*
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
        if (corePoolSize < 0 ||
                maximumPoolSize <= 0 ||
                maximumPoolSize < corePoolSize ||
                keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.acc = System.getSecurityManager() == null ?
                null :
                AccessController.getContext();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }*/
/**
 构建线程池的核心参数
 corePoolSize 核心线程数，一直保存在线程池中存活的线程
 maximumPoolSize 最大线程数，当核心线程数满了，并且等待队里也满了，则会继续创建线程，继续创建的线程数为最大线程数-核心线程数，如果还不够，则会触发拒绝策略
 keepAliveTime 非核心线程的在线程池中的空闲时间，超过这个时间，非核心先后才能会自动销毁
 unit 时间单位，配合非核心线程的存活时间一起使用
 workQueue 工作队列，当核心线程满时，会将多余的工作任务放入此工作队里中进行等待
 threadFactory 线程工厂类，专门用于创建线程
 handler 拒绝策略，当主线程满了，工作队里也满了，非核心线程也满了的时候，就会触发对应的拒绝策略啦


 线程池的的坑：

 1：工作队列是无界的，这样可能会导致非核心线程不创建，且响应变慢
 2：线程数是整数的最大值，这样可能会导致死机，因为创建的线程数太多了

 线程池的底层就一个，就是 ThreadPoolExecutor 其他的都是在他的基础上封装的

 */