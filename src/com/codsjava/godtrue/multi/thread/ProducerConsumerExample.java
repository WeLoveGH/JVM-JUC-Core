package com.codsjava.godtrue.multi.thread;

/**
 * @description：线程间通信示例
 * @author：qianyingjie1
 * @create：2019-10-21
 *
 * 生产者-消费者模型
 * 1：生产者生产一个消息放入缓冲区中
 * 2：生产者等待
 * 3：唤醒消费者
 * 4：消费者消费缓冲区的消息
 * 5：消费者等待
 * 6：唤醒生产者
 * 7：生产者总共生产十个消息，消费者总共消费十个消息
 *
 *
 *
 */
class Buffer{
    int a;
    boolean produced = false;

    public synchronized void produce(int x){
        if(produced){
            System.out.println("Producer is waiting...");
            try{
                wait();
            }catch(Exception e){
                System.out.println(e);
            }
        }
        a=x;
        System.out.println("Product" + a + " is produced.");
        produced = true;
        notify();
    }

    public synchronized void consume(){
        if(!produced){
            System.out.println("Consumer is waiting...");
            try{
                wait();
            }catch(Exception e){
                System.out.println(e);
            }
        }
        System.out.println("Product" + a + " is consumed.");
        produced = false;
        notify();
    }
}

class Producer extends Thread{
    Buffer b;
    public Producer(Buffer b){
        this.b = b;
    }

    @Override
    public void run(){
        System.out.println("Producer start producing...");
        for(int i = 1; i <= 10; i++){
            b.produce(i);
        }
    }
}

class Consumer extends Thread{
    Buffer b;
    public Consumer(Buffer b){
        this.b = b;
    }


    @Override
    public void run(){
        System.out.println("Consumer start consuming...");
        for(int i = 1; i <= 10; i++){
            b.consume();
        }
    }
}

public class ProducerConsumerExample {
    public static void main(String args[]){
        //Create Buffer object.
        Buffer b = new Buffer();

        //creating producer thread.
        Producer p = new Producer(b);

        //creating consumer thread.
        Consumer c = new Consumer(b);

        //starting threads.
        p.start();
        c.start();
    }
}
