package com.logicbig.godtrue.multi.thread.ThreadSynchronization;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-25
 */
public class Reorder {

    private int a = 2;

    private boolean flg = false;

    public void method1() {
        a = 1;
        flg = true;
    }

    public void method2() {
        if (flg) {
            //2 might be printed out on some JVM/machines
            if (a==2){
                System.out.println("a = " + a);
            }
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 1000000; i++) {

            Reorder reorderExample = new Reorder();

            Thread thread1 = new Thread(() -> {
                reorderExample.method1();
            });

            Thread thread2 = new Thread(() -> {
                reorderExample.method2();
            });

            thread1.start();
            thread2.start();
        }
    }
}