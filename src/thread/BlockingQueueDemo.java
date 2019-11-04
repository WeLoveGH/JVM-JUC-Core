package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;


/**


 概念：
     当阻塞队列为空时，获取（take）操作是阻塞的；
     当阻塞队列为满时，添加（put）操作是阻塞的。

 好处：
     阻塞队列不用手动控制什么时候该被阻塞，什么时候该被唤醒，简化了操作。

 体系：
     Collection→Queue→BlockingQueue→七个阻塞队列实现类。

 类名作用：
     ArrayBlockingQueue 由数组构成的有界阻塞队列
     LinkedBlockingQueue 由链表构成的有界阻塞队列
     PriorityBlockingQueue 支持优先级排序的无界阻塞队列
     DelayQueue 支持优先级的延迟无界阻塞队列
     SynchronousQueue 单个元素的阻塞队列
     LinkedTransferQueue 由链表构成的无界阻塞队列
     LinkedBlockingDeque 由链表构成的双向阻塞队列

     粗体标记的三个用得比较多，许多消息中间件底层就是用它们实现的。

 需要注意的是：
     LinkedBlockingQueue 虽然是有界的，但有个巨坑，其默认大小是Integer.MAX_VALUE，高达21亿，一般情况下内存早爆了（在线程池的ThreadPoolExecutor有体现）。

 API ：
     抛出异常是指当队列满时，再次插入会抛出异常；
     返回布尔是指当队列满时，再次插入会返回false；
     阻塞是指当队列满时，再次插入会被阻塞，直到队列取出一个元素，才能插入。
     超时是指当一个时限过后，才会插入或者取出。
 API使用见 BlockingQueueDemo。
     方法类型抛出异常返回布尔阻塞超时
    插入 add(E e) offer(E e) put(E e) offer(E e,Time,TimeUnit)
    取出 remove() poll() take() poll(Time,TimeUnit)
    队首 element() peek() 无无


 */

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        //addAndRemove(blockingQueue);
        //offerAndPoll(blockingQueue);
        //putAndTake(blockingQueue);
        outOfTime(blockingQueue);
    }

    private static void outOfTime(BlockingQueue<String> blockingQueue) throws InterruptedException {
        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));

        //队列满时，offer方法再放元素会返回false
        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));
    }

    private static void putAndTake(BlockingQueue<String> blockingQueue) throws InterruptedException {
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");

        // 当队列满时，put方法再放元素会阻塞
        // blockingQueue.put("d");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());

        // 当队列空时，take方法再获取元素会阻塞住
        //System.out.println(blockingQueue.take());
    }

    private static void offerAndPoll(BlockingQueue<String> blockingQueue) {
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        //队列满时，offer方法再放元素会返回false
        System.out.println(blockingQueue.offer("e"));

        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        //队列空时，再取元素会返回null
        System.out.println(blockingQueue.poll());
    }

    private static void addAndRemove(BlockingQueue<String> blockingQueue) {
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        // 当队列满时，add方法再放元素，会直接抛出异常 java.lang.IllegalStateException: Queue full
        // System.out.println(blockingQueue.add("e"));

        //获取对头的元素
        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        // 当队列空时，remove再移除元素会直接报出异常 java.util.NoSuchElementException
        System.out.println(blockingQueue.remove());
    }
}
