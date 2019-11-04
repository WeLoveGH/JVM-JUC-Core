package jvm;

/**
 * 栈满会抛出该错误。
 * 无限递归就会导致StackOverflowError，是java.lang.Throwable→java.lang.Error→java.lang.VirtualMachineError下的错误
 */
public class StackOverflowErrorDemo {
    public static void main(String[] args) {
        stackOverflowError();
    }

    private static void stackOverflowError() {
        stackOverflowError();
    }

}
