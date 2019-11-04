package com.codsjava.godtrue.multi.thread;

/**
 * @description：关闭勾子的示例
 * @author：qianyingjie1
 * @create：2019-10-22
 */
public class ShutdownHookInJava {
    public static void main(String[] args) {

        Runtime runtime=Runtime.getRuntime();

        /**
         * 当关闭虚拟机的时候，启动线程，执行一些任务
         */
        runtime.addShutdownHook(new T());

        System.out.println("Main sleeping.");

        System.out.println(Thread.currentThread().getName()+" , "+Thread.currentThread().getState());

        System.out.println("Press ctrl+c to exit.");

        try{Thread.sleep(10000);}catch (Exception e) {}
    }
}

class T extends Thread{

    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            System.out.println("Shut down hook job finished.");
        }
    }

}

/*

Java shutdown hook is used to perform some operation when JVM is shutting down.
It is mainly used to perform clean-up operations like closing log file etc.
JVM shutdown when user presses ctrl+c on the command prompt or System.exit(0) method is called or user logoff or user shutdown etc.

The Runtime.getRuntime().addShutdownHook(Thread) method is used to register the thread with the Virtual Machine.

*/
