package threadTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * Created by sickle on 17-8-24.
 */
public class countDown {
    public static CountDownLatch countDownLatch;
    public static ArrayBlockingQueue<test> arrayBlockingQueue=new ArrayBlockingQueue(2);
    public static void main(String[] args) throws InterruptedException {
    test test1=new test();
    test1.setTest(1);
    test test2=new test();
    test2.setTest(2);
    test1.start();
    test2.start();
        System.out.println(arrayBlockingQueue.take().getTest());
        System.out.println(arrayBlockingQueue.take().getTest());
    countDownLatch.await();
    }
}
class test extends Thread{
    public CountDownLatch mycountdown=new CountDownLatch(1);

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public int test;
    @Override
    public void run() {
    countDown.countDownLatch=mycountdown;

        try {
            countDown.arrayBlockingQueue.put(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
