package jvm;

import java.util.ArrayList;
import java.util.List;

//-Xms10m -Xmx10m -XX:MaxDirectMemorySize=5m -XX:+PrintGCDetails

/**
 * java.lang.OutOfMemoryError: Java heap space
 *
 *
 * 这个错误是指：GC的时候会有“Stop the World"，STW越小越好，正常情况是GC只会占到很少一部分时间。
 * 但是如果用超过98%的时间来做GC，而且收效甚微，就会被JVM叫停。
 * 下例中，执行了多次Full GC，但是内存回收很少，最后抛出了OOM:GC overhead limit exceeded错误
 */
public class GCOverheadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Exception e) {
            System.out.println("************i" + i);
            e.printStackTrace();
            throw e;
        }
    }
}
