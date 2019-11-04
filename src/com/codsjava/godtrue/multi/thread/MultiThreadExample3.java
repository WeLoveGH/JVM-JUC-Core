package com.codsjava.godtrue.multi.thread;

/**
 * @description：为静态方法加同步锁
 * @author：qianyingjie1
 * @create：2019-10-21
 */
class PrintTable3{
    public synchronized static void printTable(int n){
        System.out.println("Table of " + n);
        for(int i=1;i<=10;i++){
            System.out.println(n*i);
            try{
                Thread.sleep(500);
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
}

class MyThread13 extends Thread{
    @Override
    public void run(){
        PrintTable3.printTable(2);
    }
}

class MyThread23 extends Thread{
    @Override
    public void run(){
        PrintTable3.printTable(5);
    }
}

public class MultiThreadExample3{
    public static void main(String args[]){

        //creating threads.
        MyThread13 t1=new MyThread13();
        MyThread23 t2=new MyThread23();

        //start threads.
        t1.start();
        t2.start();
    }
}
/*
Non-Static synchronized method

        Lock is on object.
        Commonly used.
        Static keyword is not used.

Static synchronized method

        Lock is on class.
        Not commonly used.
        Used with static keyword.
*/

