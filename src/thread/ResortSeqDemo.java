package thread;

public class ResortSeqDemo {

    /**
     * 多线程环境下，指令重排序造成的问题
     */

    static int a=0;
    static boolean flag=false;
    /*
    多线程下flag=true可能先执行，还没走到a=1就被挂起。
    其它线程进入method02的判断，修改a的值=5，而不是6。
     */
    public static void method01(){
        a=1;
        flag=true;
    }
    public static void method02(){
        if (flag){
            a+=5;
            System.out.println("*****retValue: "+a);
        }
    }

    public static void main(String[] args) {

        new Thread(()->{
            method01();
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }).start();


        new Thread(()->{
            method02();
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }).start();

    }

}
