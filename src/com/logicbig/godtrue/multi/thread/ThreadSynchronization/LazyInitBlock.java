package com.logicbig.godtrue.multi.thread.ThreadSynchronization;

import java.util.ArrayList;
import java.util.List;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-24
 */
public class LazyInitBlock {

    private List<String> list;

    public static void main (String[] args) throws InterruptedException {

        LazyInitBlock obj = new LazyInitBlock();

        Thread thread1 = new Thread(() -> {
            System.out.println("thread1 : "
                    + System.identityHashCode(obj.getList())+" "
                    + System.identityHashCode(obj.getList2())+" "
                    + System.identityHashCode(obj.getList2()));
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("thread2 : "
                    + System.identityHashCode(obj.getList())+" "
                    + System.identityHashCode(obj.getList2())+" "
                    + System.identityHashCode(obj.getList2()));
        });

        thread1.start();
        thread2.start();
    }

    private List<String> getList () {
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    private synchronized List<String> getList2 () {
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    private List<String> getList3 () {
        if (list == null) {
            synchronized(this) {
                if(list == null) {
                    list = new ArrayList<>();
                }
            }
        }
        return list;
    }
}