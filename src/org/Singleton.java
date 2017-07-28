package org;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sickle on 17-7-24.
 */
//懒汉式单例类.在第一次调用的时候实例化自己
public class Singleton implements Runnable{
    static AtomicInteger atomicBoolean=new AtomicInteger();
    static int flag=0;
    private Singleton() {}
    private static Singleton single=null;
    //静态工厂方法
    public static Singleton getInstance() {
        if(atomicBoolean.compareAndSet(0,1 )) {
            if (single == null) {
                single = new Singleton();
                flag=1;
                System.out.println(Thread.currentThread().getName());
            }
        }
        return single;

    }
    @Override
    public void run() {
        getInstance();
//        str();
    }
    public void str(){
        System.out.println(Thread.currentThread().getName());
    }
    public static void main(String[] args) {
        Singleton singleton=new Singleton();
        new Thread(singleton).start();
        new Thread(singleton).start();
        new Thread(singleton).start();
        new Thread(singleton).start();
    }


}