package org;

import java.io.IOException;

/**
 * Created by sickle on 17-7-24.
 */
class Lock{

}
public class SynchronizedTest implements Runnable{
    Lock lock=new Lock();

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    private int num;
    @Override
    public void run() {
        taker();
    }
    private void taker(){     //private  synchronized void taker()  函数锁，一个class内的函数都上锁
        synchronized (lock){//堆锁，  this.lock
            if(num>0){
                num--;
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedTest ss=new SynchronizedTest();
        ss.setNum(10000);
        for(int i=0;i<1000;i++){
            new Thread(ss).start();
        }
        try {
            System.in.read();
            System.out.println(ss.getNum());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}