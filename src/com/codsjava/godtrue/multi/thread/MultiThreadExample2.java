package com.codsjava.godtrue.multi.thread;

/**
 * @description：加同步锁的多线程示例，加了同步器之后，同步方法的内容就是原子了啦
 * @author：qianyingjie1
 * @create：2019-10-21
 */
class PrintTable2{
    //synchronized method.
    public synchronized void printTable(int n){
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

class MyThread12 extends Thread{
    PrintTable2 pt;
    MyThread12(PrintTable2 pt){
        this.pt=pt;
    }

    @Override
    public void run(){
        pt.printTable(2);
    }
}

class MyThread22 extends Thread{
    PrintTable2 pt;
    MyThread22(PrintTable2 pt){
        this.pt=pt;
    }

    @Override
    public void run(){
        pt.printTable(5);
    }
}

public class MultiThreadExample2{
    public static void main(String args[]){
        //creating PrintTable object.
        PrintTable2 obj = new PrintTable2();

        //creating threads.
        MyThread12 t1=new MyThread12(obj);
        MyThread22 t2=new MyThread22(obj);

        //start threads.
        t1.start();
        t2.start();
    }
}
