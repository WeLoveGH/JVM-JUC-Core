package jvm;


/**
 * 使用new方法创造出来的对象，默认都是强引用。
 * GC的时候，就算内存不够，抛出OutOfMemoryError也不会回收对象，死了也不回收。
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object o1=new Object();
        Object o2=new Object();
        o1=null;
        System.gc();
        System.out.println(o2);
    }
}
