package com.codsjava.godtrue.multi.thread;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @description：整型数组示例
 * @author：qianyingjie1
 * @create：2019-10-22
 */
public class AtomicIntegerArrayInJava {
    public static void main(String[] args) {

        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{10,41,25,23});

        //23
        System.out.println(atomicIntegerArray.get(3));

        //63
        System.out.println(atomicIntegerArray.addAndGet(3,40));

        //25
        System.out.println(atomicIntegerArray.getAndAdd(2,30));

        //55
        System.out.println(atomicIntegerArray.get(2));
    }
}
/*

The java.util.concurrent.atomic.AtomicIntegerArray class represents an array of int which are updated atomically.

*/
