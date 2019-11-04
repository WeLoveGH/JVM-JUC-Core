package com.codsjava.godtrue.multi.thread;

/**
 * @description：给代码块加同步锁
 * @author：qianyingjie1
 * @create：2019-10-21
 */
class PrintTable4{
    public void printTable(int n){
        synchronized(this){
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
}

class MyThread14 extends Thread{
    PrintTable4 pt;
    MyThread14(PrintTable4 pt){
        this.pt=pt;
    }

    @Override
    public void run(){
        pt.printTable(2);
    }
}

class MyThread24 extends Thread{
    PrintTable4 pt;
    MyThread24(PrintTable4 pt){
        this.pt=pt;
    }

    @Override
    public void run(){
        pt.printTable(5);
    }
}

public class MultiThreadExample4{
    public static void main(String args[]){
        //creating PrintTable object.
        PrintTable4 obj = new PrintTable4();

        //creating threads.
        MyThread14 t1=new MyThread14(obj);
        MyThread24 t2=new MyThread24(obj);

        //start threads.
        t1.start();
        t2.start();
    }
}

/*

Synchronized method
    Lock is acquired on whole method.
    Less preferred.
    Performance will be less as compared to synchronized block.

Synchronized block
    Lock is acquired on critical block of code only.
    preferred.
    Performance will be better as compared to synchronized method.

*/
