package com.logicbig.godtrue.multi.thread.Synchronizers;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CyclicBarrier;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-29
 *
In Java, a CyclicBarrier allows a set of threads (parties) to all wait for each other to reach a common barrier point.

A CyclicBarrier is initialized with a given count (the parties).
Let's say this count is N.

The method CyclicBarrier#await() decreases the count N by 1.
The calling threads waits until N becomes zero i.e. all threads (parties) have invoked await() on this barrier.

There is another overloaded method await(long timeout, TimeUnit unit), which can specify a max waiting time.

CyclicBarrier can be useful for multiple purposes,
for example a number of threads can be paused for other threads to reach to a certain point before progressing.
This functionality is similar to CountDownLatch, but main difference with CyclicBarrier is; it does not have method countDown().
The method CyclicBarrier#await() takes care of both blocking the threads at the barrier point and decreasing the underlying count.
Also unlike CountDownLatch, CyclicBarrier can reuse the underlying count over and over again.
CountDownLatch's underlying count when reaches to zero, cannot be reset to the initial count.

CyclicBarrier is synchronized internally, so it can be used by multiple threads safely.
Also actions in a thread before calling await() establish happen-before relation with all actions following a successful return from await() in this and all other threads.



 循环栏删和倒数门闩计数器的功能类似，都是控制等待其线程完成某些事情，然后再一起往下继续执行，他们的不同点在于

 CountDownLatch
 1：减法计数
 2：有 countDown 方法，计数器的计数值减一
 3：不能循环使用

 CyclicBarrier
 1：减法计数
 2：没有 countDown 方法
 3：能循环使用
 4：await 方法的功能不但阻塞当前运行的线程，而且会将计数器的计数值加一
    当 await 方法调用次数等于构建时指定的数值，则会计数器的计数值会被重置为 0 ，这样就能开始下一轮的计数操作了
 *
 *
 *
 *
 */
public class CyclicBarrierExample {
    /**
     * 定义线程数为 4
     */
    private static final int THREAD_COUNT = 4;
    public static void main(String[] args) {
        /**
         * 构建循环栏删，线程数为 4
         */
        CyclicBarrier barrier = new CyclicBarrier(THREAD_COUNT);

        /**
         * 构建窗口，设置窗口
         */
        JFrame frame = createFrame();
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));

        /**
         * 构建四个进度条，四个线程，放入窗体之中，且启动
         */
        for (int i = 1; i <= THREAD_COUNT; i++) {
            ProgressThreadForCyclicBarrier progressThread = new ProgressThreadForCyclicBarrier(barrier, i * 10);
            frame.add(progressThread.getProgressComponent());
            progressThread.start();
        }
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JFrame createFrame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Java CyclicBarrier Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(ProgressThread.PROGRESS_WIDTH, 170));
        return frame;
    }
}

class ProgressThreadForCyclicBarrier extends Thread {
    public static final int PROGRESS_WIDTH = 350;
    private static final int CATCH_UP_MULTIPLE = 25;
    private final JProgressBar progressBar;
    private final CyclicBarrier barrier;
    private final int slowness;

    public ProgressThreadForCyclicBarrier(CyclicBarrier barrier, int slowness) {
        this.barrier = barrier;
        this.slowness = slowness;
        progressBar = new JProgressBar();
        progressBar.setPreferredSize(
                new Dimension(PROGRESS_WIDTH - 30, 25));
        progressBar.setStringPainted(true);
    }

    JComponent getProgressComponent() {
        return progressBar;
    }


    /**
     * 复写线程类的 run 方法
     *
     */
    @Override
    public void run() {
        /**
         * 进度的初始值
         */
        int c = 0;

        /**
         * 控制进度变化的方法
         * false : 增加进度
         * true : 减少进度
         */
        boolean reversed = false;

        //死循环
        while (true) {
            /**
             * 控制进度的变化，增加或减少
             */
            progressBar.setValue(reversed ? --c : ++c);

            /**
             * 当进度等于 100 时，减少进度
             * 当进度等于 0 时，增加进度
             */
            if (c == 100) {
                reversed = true;
            } else if (c == 0) {
                reversed = false;
            }

            /**
             * 休眠，不同的线程休眠时间不一样，分别Wie：10/20/30/40 ms
             */
            try {
                Thread.sleep(slowness);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //if c is multiple of CATCH_UP_MULTIPLE
            /**
             * 当进度对 25 取模等于 0 时，等待其他线程
             * 即进度为 0/25/50/75/100
             */
            if (c % CATCH_UP_MULTIPLE == 0) {

                System.out.println(Thread.currentThread().getName()+" 当前进度为："+c+" 调用 await 之前，parties:"+barrier.getParties()+"， count:"+(barrier.getParties()-barrier.getNumberWaiting()+"， numberWaiting（parties - count）:"+barrier.getNumberWaiting()));

                try {
                    /**
                     * This is the barrier point.
                     * On calling await() method, the current thread (party)
                     * will wait until all threads (parties) have called this
                     * method. At this method call
                     * barrier.getNumberWaiting() will increase by one.*/
                    barrier.await();

                    System.out.println(Thread.currentThread().getName()+" 当前进度为："+c+" 调用 await 之后，parties:"+barrier.getParties()+"， count:"+(barrier.getParties()-barrier.getNumberWaiting()+"， numberWaiting（parties - count）:"+barrier.getNumberWaiting()));

                    //at this point no threads will be waiting and
                    // barrier.getNumberWaiting() will be zero
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/*

The CyclicBarrier logic from above example can be used with some useful multiple tasks
which want to submit their individual work at the barrier point and then start with some initial parameters again

*/


/*

parties:
       The number of parties

count:
       Number of parties still waiting.
       Counts down from parties to 0 on each generation.
       It is reset to parties on each new generation or when broken.

numberWaiting:
       Returns the number of parties currently waiting at the barrier.
       This method is primarily useful for debugging and assertions.

numberWaiting  = parties - count

"C:\Program Files\Java\jdk1.8.0_162\bin\java"
"-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2017.2.2\lib\idea_rt.jar=64153:C:\Program Files\JetBrains\IntelliJ IDEA 2017.2.2\bin"
-Dfile.encoding=UTF-8
-classpath "
C:\Program Files\Java\jdk1.8.0_162\jre\lib\charsets.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\deploy.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\access-bridge-64.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\cldrdata.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\dnsns.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\jaccess.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\jfxrt.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\localedata.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\nashorn.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\sunec.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\sunjce_provider.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\sunmscapi.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\sunpkcs11.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\zipfs.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\javaws.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\jce.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\jfr.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\jfxswt.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\jsse.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\management-agent.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\plugin.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\resources.jar;
C:\Program Files\Java\jdk1.8.0_162\jre\lib\rt.jar;
D:\workspace_test\JVM-JUC-Core\out\production\JVM-JUC-Core"
com.logicbig.godtrue.multi.thread.Synchronizers.CyclicBarrierExample


从打印的日志中可以看出
1：初始化的线程数不变
2：计数器的计数值，也是不断的减一的
3：当计数器的计数值减为 0 时，此时计数器的计数值会重置为 4
4：这个计数器是可以重复循环利用的
5：后续被阻塞的线程被唤醒，再次运行时，谁先谁后是非公平的，随机的

Thread-1 当前进度为：25 调用 await 之前，parties:4， count:4， numberWaiting（parties - count）:0
Thread-2 当前进度为：25 调用 await 之前，parties:4， count:3， numberWaiting（parties - count）:1
Thread-3 当前进度为：25 调用 await 之前，parties:4， count:2， numberWaiting（parties - count）:2
Thread-4 当前进度为：25 调用 await 之前，parties:4， count:1， numberWaiting（parties - count）:3

Thread-2 当前进度为：25 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-3 当前进度为：25 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-4 当前进度为：25 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-1 当前进度为：25 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0



Thread-1 当前进度为：50 调用 await 之前，parties:4， count:4， numberWaiting（parties - count）:0
Thread-2 当前进度为：50 调用 await 之前，parties:4， count:3， numberWaiting（parties - count）:1
Thread-3 当前进度为：50 调用 await 之前，parties:4， count:2， numberWaiting（parties - count）:2
Thread-4 当前进度为：50 调用 await 之前，parties:4， count:1， numberWaiting（parties - count）:3

Thread-4 当前进度为：50 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-1 当前进度为：50 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-2 当前进度为：50 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-3 当前进度为：50 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0



Thread-1 当前进度为：75 调用 await 之前，parties:4， count:4， numberWaiting（parties - count）:0
Thread-2 当前进度为：75 调用 await 之前，parties:4， count:3， numberWaiting（parties - count）:1
Thread-3 当前进度为：75 调用 await 之前，parties:4， count:2， numberWaiting（parties - count）:2
Thread-4 当前进度为：75 调用 await 之前，parties:4， count:1， numberWaiting（parties - count）:3

Thread-4 当前进度为：75 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-1 当前进度为：75 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-2 当前进度为：75 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-3 当前进度为：75 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0



Thread-1 当前进度为：100 调用 await 之前，parties:4， count:4， numberWaiting（parties - count）:0
Thread-2 当前进度为：100 调用 await 之前，parties:4， count:3， numberWaiting（parties - count）:1
Thread-3 当前进度为：100 调用 await 之前，parties:4， count:2， numberWaiting（parties - count）:2
Thread-4 当前进度为：100 调用 await 之前，parties:4， count:1， numberWaiting（parties - count）:3

Thread-4 当前进度为：100 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-3 当前进度为：100 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-2 当前进度为：100 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-1 当前进度为：100 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0



Thread-1 当前进度为：75 调用 await 之前，parties:4， count:4， numberWaiting（parties - count）:0
Thread-2 当前进度为：75 调用 await 之前，parties:4， count:3， numberWaiting（parties - count）:1
Thread-3 当前进度为：75 调用 await 之前，parties:4， count:2， numberWaiting（parties - count）:2
Thread-4 当前进度为：75 调用 await 之前，parties:4， count:1， numberWaiting（parties - count）:3
Thread-4 当前进度为：75 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-1 当前进度为：75 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-3 当前进度为：75 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-2 当前进度为：75 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-1 当前进度为：50 调用 await 之前，parties:4， count:4， numberWaiting（parties - count）:0
Thread-2 当前进度为：50 调用 await 之前，parties:4， count:3， numberWaiting（parties - count）:1
Thread-3 当前进度为：50 调用 await 之前，parties:4， count:2， numberWaiting（parties - count）:2
Thread-4 当前进度为：50 调用 await 之前，parties:4， count:1， numberWaiting（parties - count）:3
Thread-4 当前进度为：50 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-1 当前进度为：50 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-2 当前进度为：50 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-3 当前进度为：50 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-1 当前进度为：25 调用 await 之前，parties:4， count:4， numberWaiting（parties - count）:0
Thread-2 当前进度为：25 调用 await 之前，parties:4， count:3， numberWaiting（parties - count）:1
Thread-3 当前进度为：25 调用 await 之前，parties:4， count:2， numberWaiting（parties - count）:2
Thread-4 当前进度为：25 调用 await 之前，parties:4， count:1， numberWaiting（parties - count）:3
Thread-4 当前进度为：25 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-1 当前进度为：25 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-2 当前进度为：25 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-3 当前进度为：25 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-1 当前进度为：0 调用 await 之前，parties:4， count:4， numberWaiting（parties - count）:0
Thread-2 当前进度为：0 调用 await 之前，parties:4， count:3， numberWaiting（parties - count）:1
Thread-3 当前进度为：0 调用 await 之前，parties:4， count:2， numberWaiting（parties - count）:2
Thread-4 当前进度为：0 调用 await 之前，parties:4， count:1， numberWaiting（parties - count）:3
Thread-4 当前进度为：0 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-3 当前进度为：0 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-1 当前进度为：0 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0
Thread-2 当前进度为：0 调用 await 之后，parties:4， count:4， numberWaiting（parties - count）:0

Process finished with exit code 0


*/
