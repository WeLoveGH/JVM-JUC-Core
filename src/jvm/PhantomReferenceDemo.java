package jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 软引用和弱引用可以通过get()方法获得对象，但是虚引用不行。
 * 虚引用形同虚设，在任何时候都可能被GC，不能单独使用，必须配合 引用队列（ReferenceQueue）来使用。
 * 设置虚引用的唯一目的，就是在这个对象被回收时，收到一个通知 以便进行后续操作，有点像Spring的后置通知
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference phantomReference = new PhantomReference(o1, referenceQueue);
        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("===========");

        o1 = null;
        System.gc();
        Thread.sleep(500);
        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }
}
