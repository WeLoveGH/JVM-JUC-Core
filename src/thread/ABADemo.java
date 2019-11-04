package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;


/**
 * 所谓ABA问题，就是比较并交换的循环，存在一个时间差，而这个时间差可能带来意想不到的问题。
 * 比如线程T1将值从A改为B，然后又从B改为A。
 * 线程T2看到的就是A，但是却不知道这个A发生了更改 。
 * 尽管线程T2 CAS操作成功，但不代表就没有问题。
 * 有的需求，比如CAS，只注重头和尾 ，只要首尾一致就接受。
 * 但是有的需求，还看重过程，中间不能发生任何修改，这就引出了AtomicReference原子引用
 *
 *
 * 使用AtomicStampedReference类可以解决ABA问题。这个类维护了一个“版本号”Stamp，在进行CAS操作的时候，不仅要比较当前值，还要比较版本号。
 * 只有两者都相等，才执行更新操作。
 *
 */
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        System.out.println("======ABA问题的产生======");

        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
            System.out.println(atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.get().toString());
        }, "t2").start();

        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("======ABA问题的解决======");
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第一次版本号： " + stamp);
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            atomicStampedReference.compareAndSet(100,101,
                    atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t第二次版本号： " + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,
                    atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t第三次版本号： " + atomicStampedReference.getStamp());
        }, "t3").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第一次版本号： " + stamp);
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            boolean result=atomicStampedReference.compareAndSet(100,2019,
                    stamp,stamp+1);
            System.out.println(Thread.currentThread().getName()+"\t修改成功与否："+result+"  当前最新版本号"+atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName()+"\t当前实际值："+atomicStampedReference.getReference());
        }, "t4").start();
    }
}
