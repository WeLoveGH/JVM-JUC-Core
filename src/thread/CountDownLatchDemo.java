package thread;


// CountDownLatch 门栓类 ，优雅控制所有子线程都执行完毕，统一在主线程那儿汇合

import java.util.concurrent.CountDownLatch;


/**

 CountDownLatch 门栓类 ，优雅控制所有子线程都执行完毕，统一在主线程那儿汇合

 CountDownLatch	                                                      CyclicBarrier
 减计数方式	                                                          加计数方式
 计算为0时释放所有等待的线程	                                          计数达到指定值时释放所有等待线程
 计数为0时，无法重置	                                                  计数达到指定值时，计数置为0重新开始
 调用countDown()方法计数减一，调用await()方法只进行阻塞，对计数没任何影响	  调用await()方法计数加1，若加1后的值不等于构造方法的值，则线程阻塞
 不可重复利用	                                                          可重复利用

 */

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        leaveClassroom();
        county();
    }

    private static void county() throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(6);
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 国被灭");
                countDownLatch.countDown();
            }, CountryEnum.list(i).getRetMsg()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t ******秦国一统华夏");
    }

    private static void leaveClassroom() throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(6);
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t上完自习，离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t ******班长最后关门走人");
    }
}
