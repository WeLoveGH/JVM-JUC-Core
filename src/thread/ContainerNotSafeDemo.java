package thread;

import java.util.*;

public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        //listNotSafe();
        //setNoSafe();
        mapNotSafe();
    }

    private static void mapNotSafe() {

        // 使用 HashMap 可能会抛出 java.util.ConcurrentModificationException
        Map<String,String> map=new HashMap<>();
        //Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(Thread.currentThread().getName() + "\t" + map);
            }, String.valueOf(i)).start();
        }
    }

    private static void setNoSafe() {
        // 使用 HashSet 可能抛出 java.util.ConcurrentModificationException
        Set<String> set=new HashSet<>();
        //Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(Thread.currentThread().getName() + "\t" + set);
            }, String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {

        // 使用ArrayList会抛出 java.util.ConcurrentModificationException
        List<String> list=new ArrayList<>();
        //List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(Thread.currentThread().getName() + "\t" + list);
            }, String.valueOf(i)).start();
        }
    }
}
