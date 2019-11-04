package com.codsjava.godtrue.multi.thread;

/**
 * @description：重写start方法
 * @author：qianyingjie1
 * @create：2019-10-17
 */
public class CallStartTwice {
    public static void main(String[] args) {

        Thread t = new MyThreadForT5();
        t.start();
        t.start();
    }
}

class MyThreadForT5 extends Thread{
    /**
     * Causes this thread to begin execution; the Java Virtual Machine
     * calls the <code>run</code> method of this thread.
     * <p>
     * The result is that two threads are running concurrently: the
     * current thread (which returns from the call to the
     * <code>start</code> method) and the other thread (which executes its
     * <code>run</code> method).
     * <p>
     * It is never legal to start a thread more than once.
     * In particular, a thread may not be restarted once it has completed
     * execution.
     *
     * @throws IllegalThreadStateException if the thread was already
     *                                     started.
     * @see #run()
     * @see #stop()
     */
    @Override
    public synchronized void start() {
        System.out.println("i am MyThreadForT5 start");
        //super.start();
    }
}
