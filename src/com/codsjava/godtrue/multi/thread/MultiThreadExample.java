package com.codsjava.godtrue.multi.thread;

/**
 * @description：不加同步器的多线程示例，不加同步器，线程执行的治指令会被打断
 * @author：qianyingjie1
 * @create：2019-10-21
 */
class PrintTable{
    //not-synchronized method.
    public void printTable(int n){
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

class MyThread1 extends Thread{
    PrintTable pt;
    MyThread1(PrintTable pt){
        this.pt=pt;
    }

    @Override
    public void run(){
        pt.printTable(2);
    }
}

class MyThread2 extends Thread{
    PrintTable pt;
    MyThread2(PrintTable pt){
        this.pt=pt;
    }

    @Override
    public void run(){
        pt.printTable(5);
    }
}

public class MultiThreadExample{
    public static void main(String args[]){
        //creating PrintTable object.
        PrintTable obj = new PrintTable();

        //creating threads.
        MyThread1 t1=new MyThread1(obj);
        MyThread2 t2=new MyThread2(obj);

        //start threads.
        //t1.start();
        t2.start();
    }
}
