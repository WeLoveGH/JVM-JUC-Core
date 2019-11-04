package jvm;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * 传统的HashMap就算key==null了，也不会回收键值对。
 * 但是如果是WeakHashMap，一旦内存不够用时，且key==null时，会回收这个键值对
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        myHashMap();
        System.out.println("===============");
        myWeakHashMap();
    }

    private static void myHashMap() {
        HashMap<Integer, String> map = new HashMap<>();
        Integer key = 1;
        String value = "HashMap";
        map.put(key, value);
        System.out.println(map);

        key = null;
        map.put(key,"key is null");
        System.out.println(map);
        System.gc();
        System.out.println(map + "\t" + map.size());
    }

    private static void myWeakHashMap() {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = 2;
        String value = "WeakHashMap";
        map.put(key, value);
        System.out.println(map);

        key = null;
        map.put(key,"key is null");
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t" + map.size());
    }
}
