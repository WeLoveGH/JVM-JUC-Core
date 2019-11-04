package com.codsjava.godtrue.multi.thread;

/**
 * @description：线程是什么
 * @author：qianyingjie1
 * @create：2019-10-17
 */
public class JvmCreateDeamonThreads {

    /**
     * 如下是一个非常简单的程序代码，一个主方法，死循环打印日志
     * 我们自己没有创建任何线程，不过通过 Java VisulaVM 工具查看的话，会发现有12个线程是存活的，
     * 其中11个线程为守护线程，一个线程为用户线程，这个用户线程就是主线程
     *
     * 其他的线程为：
     * Reference Handler
     * Finalizer
     * Signal Dispatcher
     * Attach Listener
     * Monitor Ctrl-Break
     * RMI TCP Accept-0
     * RMI TCP Scheduler
     * JMX server connection timeout 15
     * RMI TCP Connection(1)-10.1.160.182
     * RMI TCP Connection(2)-10.1.160.182
     * RMI TCP Connection(3)-10.1.160.182
     *
     * @param args
     */

    public static void main(String[] args) {
        while (true){
            System.out.println("args = [" + args + "]");
        }
    }

}
