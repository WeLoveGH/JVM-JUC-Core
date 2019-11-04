package com.codsjava.godtrue.multi.thread;

import java.util.concurrent.atomic.AtomicLongArray;

/**
 * @description：长整型数组示例
 * @author：qianyingjie1
 * @create：2019-10-22
 */
public class AtomicLongArrayInJava {
    public static void main(String[] args) {
        AtomicLongArray atomicLongArray = new AtomicLongArray(new long[]{10,41,25,23});

        //23
        System.out.println(atomicLongArray.get(3));

        //63
        System.out.println(atomicLongArray.addAndGet(3,40));

        //25
        System.out.println(atomicLongArray.getAndAdd(2,30));

        //55
        System.out.println(atomicLongArray.get(2));
    }
}
