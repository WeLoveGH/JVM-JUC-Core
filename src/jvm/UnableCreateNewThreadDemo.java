package jvm;

/**
 *
 在高并发应用场景时，如果创建超过了系统默认的最大线程数，就会抛出该异常。
 Linux单个进程默认不能超过1024个线程。解决方法
 要么降低程序线程数，
 要么修改系统最大线程数vim /etc/security/limits.d/90-nproc.con
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for (int i = 0; ; i++) {
            System.out.println("***********" + i);
            new Thread(() -> {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "" + i).start();
        }
    }
}

/**
 * -XX:+PrintCommandLineFlags 这个参数的作用
 *
        -XX:InitialHeapSize=196346816
        -XX:MaxHeapSize=3141549056
        -XX:+PrintCommandLineFlags
        -XX:+UseCompressedClassPointers
        -XX:+UseCompressedOops
        -XX:-UseLargePagesIndividualAllocation
        -XX:+UseParallelGC
*/