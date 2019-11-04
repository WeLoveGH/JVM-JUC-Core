package thread;

public class SingletonDemo {

    private static SingletonDemo singletonDemo=null;

    //private static volatile SingletonDemo singletonDemo=null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法");
    }

    //DCL模式 Double Check Lock 双端检索机制：在加锁前后都进行判断
    public static SingletonDemo getInstance(){

        if (singletonDemo==null){
            synchronized (SingletonDemo.class){
                if (singletonDemo==null){
                    singletonDemo=new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            },String.valueOf(i+1)).start();
        }
    }
}

/**
 *
 * 单例模式的写法有许多：
 * 饿汉模式
 * 懒汉模式
 * 双重加锁检测
 * 使用内部类构造
 * 使用枚举类构造
 *
 *
 */
