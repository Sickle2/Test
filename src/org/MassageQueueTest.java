package org;



import java.util.Random;
import java.util.concurrent.*;
/**
 * Created by sickle on 17-7-26.
 */
class producer implements Runnable{
//    public ArrayBlockingQueue<String> blockingQueue=null;
//    public producer(ArrayBlockingQueue<String> blockingQueue) {
//        this.blockingQueue = blockingQueue;
//    }
    public void add() throws InterruptedException {
        MassageQueueTest.blockingQueue.put(String.valueOf(new Random().nextInt(100)));
//                System.out.println(Thread.currentThread().getName());
    }
    @Override
    public void run() {
        while (true) {
            try {
                add();
//                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class customer implements Runnable{
//    public ArrayBlockingQueue<String> blockingQueue=null;
//    public customer(ArrayBlockingQueue<String> blockingQueue) {
//        this.blockingQueue = blockingQueue;
//    }
    public void take() throws InterruptedException {
        String sss=MassageQueueTest.blockingQueue.take();
        System.out.println(sss );
//            System.out.println(Thread.currentThread().getName());
    }
    @Override
    public void run() {
        while(true) {
            try {
                take();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class MassageQueueTest {
    static ArrayBlockingQueue<String> blockingQueue= new ArrayBlockingQueue<String>(5);
    public static void main(String[] args) throws InterruptedException {

        producer producer=new producer( );
        customer customer=new customer();
        ExecutorService pool=Executors.newScheduledThreadPool(2);
        pool.submit(new Thread(producer));
        pool.submit(new Thread(customer));
        pool.shutdown();
    }      //修改。static
}