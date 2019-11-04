package com.logicbig.godtrue.multi.thread.ExecutorFramework;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-28
 */
public class MyTask implements Runnable {

    private static final long start;

    private final String name;

    private long repeatCount;

    private long taskStart;

    static {
        start = System.currentTimeMillis();
    }

    public MyTask (String s) {
        this.name = s;
    }

    @Override
    public void run () {
        taskStart = System.currentTimeMillis();
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        repeatCount++;
        printTaskInfo();
    }

    private void printTaskInfo () {
        StringBuilder builder = new StringBuilder(" ")
                .append(name)
                .append(" - Repeat count: ")
                .append(repeatCount)
                .append(" - Exec At: ")
                .append(taskStart - start)
                .append(" - Task duration: " + (System.currentTimeMillis()
                        - taskStart));

        System.out.println(builder);
    }

    public String getName () {
        return name;
    }
}