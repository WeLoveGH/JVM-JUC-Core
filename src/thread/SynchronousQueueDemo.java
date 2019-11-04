package thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue=new SynchronousQueue<String>();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"\t put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"\t put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"生产线程").start();

        new Thread(()->{
            try {
                try{ TimeUnit.SECONDS.sleep(5); }catch (InterruptedException e){ e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+"\t take "+blockingQueue.take());
                try{ TimeUnit.SECONDS.sleep(5); }catch (InterruptedException e){ e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+"\t take "+blockingQueue.take());
                try{ TimeUnit.SECONDS.sleep(5); }catch (InterruptedException e){ e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+"\t take"+blockingQueue.take());
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"消费线程").start();
    }

    /**
     *
     * 队列只有一个元素，如果想插入多个，必须等队列元素取出后，才能插入，只能有一个“坑位”，用一个插一个。
     *
     */
}
