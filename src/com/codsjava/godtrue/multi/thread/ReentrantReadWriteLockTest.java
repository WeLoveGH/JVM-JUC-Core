package com.codsjava.godtrue.multi.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description：读写锁示例
 * @author：qianyingjie1
 * @create：2019-10-21
 */
public class ReentrantReadWriteLockTest<E> {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    private final List<E> list = new ArrayList<>();
    public void set(E o){
        writeLock.lock();
        try{
            list.add(o);
            System.out.println("Add element: "+Thread.currentThread().getName());
        } finally {
            writeLock.unlock();
        }
    }

    public E get(int i) {
        readLock.lock();
        try	{
            System.out.println("Print elements: "+Thread.currentThread().getName());
            return list.get(i);
        } finally {
            readLock.unlock();
        }
    }
    public static void main(String[] args) {
        ReentrantReadWriteLockTest<String> lockTest = new ReentrantReadWriteLockTest<>();

        lockTest.set("1");
        lockTest.set("2");
        lockTest.set("3");

        System.out.println("First Element: "+lockTest.get(1));
    }
}


/*

The ReadWriteLock is an interface which provides an advanced thread lock mechanism. It maintained a pair of locks (one for read only and other for write). The basic idea for ReadWriteLock is to increase the performance by allowing multiple threads to read a shared resource, when no thread is writing.

    Note: Only one thread can write the shared resource value at a time.

    ReadWriteLock locking rules:

    Read Lock: If no thread have or not requested ReadWriteLock for writing then multiple threads can lock the read lock for shared resource.

    Write Lock: If no thread have read or write lock then one thread can lock write lock on shared resource.

    Note: It performs best when there are more read locks required as compared to write locks.

*/
