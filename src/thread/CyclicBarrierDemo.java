package thread;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) {


/**

 CountDownLatch 门栓类 ，优雅控制所有子线程都执行完毕，统一在主线程那儿汇合

 CountDownLatch	                                                      CyclicBarrier
 减计数方式	                                                          加计数方式
 计算为0时释放所有等待的线程	                                          计数达到指定值时释放所有等待线程
 计数为0时，无法重置	                                                  计数达到指定值时，计数置为0重新开始
 调用countDown()方法计数减一，调用await()方法只进行阻塞，对计数没任何影响	  调用await()方法计数加1，若加1后的值不等于构造方法的值，则线程阻塞
 不可重复利用	                                                          可重复利用

 */

        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{
            System.out.println("*****召唤神龙");
        });

        for (int i = 1; i <=7 ; i++) {
            final int tempInt=i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+
                        "\t 收集到第"+tempInt+"颗龙珠");
                try{
                    cyclicBarrier.await();
                }catch (Exception e){
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

    }

}
