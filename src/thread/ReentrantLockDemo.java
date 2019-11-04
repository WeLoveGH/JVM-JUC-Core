package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {


    /**
     *

     可重入锁又叫递归锁，指的同一个线程在外层方法获得锁时，进入内层方法 会自动获取锁。
     也就是说，线程可以进入任何一个它已经拥有锁的代码块。
     比如get方法里面有set方法，两个方法都有同一把锁，得到了get的锁，就自动得到了set的锁。
     就像有了家门的锁，厕所、书房、厨房就为你敞开了一样。
     可重入锁可以避免死锁的问题。

     */


    public static void main(String[] args) {
        Phone phone=new Phone();
        syncTest(phone);
        System.out.println();

        Thread t3=new Thread(phone);
        Thread t4=new Thread(phone);

        t3.start();
        t4.start();

    }

    private static void syncTest(Phone phone) {

        new Thread(()->{
            try{
                phone.sendSMS();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try{
                phone.sendSMS();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"t2").start();
    }
}
class Phone implements Runnable{
    //Synchronized TEST

    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getId()+"\t"+"sendSMS()");
        sendEmail();
    }
    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getId()+"\t"+"sendEmail()");
    }

    //Reentrant TEST

    Lock lock=new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getId()+"\t"+"get()");
            set();
        }finally {
            lock.unlock();
        }
    }
    public void set(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getId()+"\t"+"set()");
        }finally {
            lock.unlock();
        }
    }
}
