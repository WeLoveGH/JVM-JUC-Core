package com.logicbig.godtrue.multi.thread.ExecutorFramework;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-28
 */
public class PoolUtil {

    public static void showPoolDetails (ThreadPoolExecutor executor, String tasksDetails) {
        StringBuilder builder = new StringBuilder();
        builder.append("Executor: ")
                .append(executor.getClass())
                .append("\ncore pool size: ")
                .append(executor.getCorePoolSize())
                .append("\nTask submitted: ")
                .append(executor.getTaskCount())
                .append("\nQueue: ")
                .append(executor.getQueue()
                        .getClass())
                .append("\nTasks details: ")
                .append(tasksDetails)
                .append("\n");
        System.out.println(builder);
    }
}