package thread;

/**
 * @description：测试没有主线程时jvm自己创建了几个线程
 * @author：qianyingjie1
 * @create：2019-10-17
 */
public class MainThreadDemo {
    public static void main(String[] args) {
        while (true){
            System.out.println("args = [" + args + "]");
        }
    }
}
