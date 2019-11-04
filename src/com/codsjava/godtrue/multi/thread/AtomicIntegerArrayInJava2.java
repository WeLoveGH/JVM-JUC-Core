package com.codsjava.godtrue.multi.thread;

/**
 * @description：整型数组示例
 * @author：qianyingjie1
 * @create：2019-10-22
 */
public class AtomicIntegerArrayInJava2 {
    public static void main(String[] args) {

        Integer[] integerArray = {10,41,25,23};

        //23
        System.out.println(integerArray[3]);

        //63
        System.out.println((integerArray[3]+40));

        //55
        System.out.println((integerArray[2]+30));

        //25
        System.out.println(integerArray[2]);
    }
}
