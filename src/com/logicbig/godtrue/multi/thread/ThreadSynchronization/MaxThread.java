package com.logicbig.godtrue.multi.thread.ThreadSynchronization;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-25
 */
public class MaxThread {
    public static void main(String[] args) {

        /**
         * 这个程序会把你的机器跑死，所以，不要轻易启动
         */
        List threadList = new ArrayList();
        while (true) {

            Thread thread = new Thread(() -> {
                System.out.println(new Date() + " : " + Thread.currentThread().getName());
                while (true){}
            });

            threadList.add(thread);

            thread.start();
        }

    }
}
