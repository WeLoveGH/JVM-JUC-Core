package com.codsjava.godtrue.multi.thread;

/**
 * @description：创建线程的方式
 * @author：qianyingjie1
 * @create：2019-10-17
 */
public class HowCreateThread {
    public static void main(String[] args) {
        Thread myThread = new MyThread();
        myThread.setName("通过继承Thread类构造线程对象");
        myThread.start();

        MyRunnable myRunnable = new MyRunnable();
        Thread myRunnableThread = new Thread(myRunnable);
        myRunnableThread.setName("通过实现Runnable接口构造线程对象");
        myRunnableThread.start();
    }
}

class MyThread extends Thread{
    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     * @see #Thread(ThreadGroup, Runnable, String)
     */
    @Override
    public void run() {
        System.out.println("i am " + Thread.currentThread().getName());
    }
}

class MyRunnable implements Runnable{
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println("i am " + Thread.currentThread().getName());
    }
}
