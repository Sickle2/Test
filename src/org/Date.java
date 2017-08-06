package org;

import java.util.*;
import java.util.logging.SimpleFormatter;

/**
 * Created by sickle on 17-6-24.
 */
abstract class base{
    public boolean ReadTTY(String tty){
        Object t=new Object();
        read(t);
        return true;
    }
    abstract protected boolean read(Object t);
}
class MyRead extends base{
    @Override
    protected boolean read(Object t) {
        return true;
    }
}
public class Date {
    public static void main(String[] args) {
        base b=new MyRead();
        b.ReadTTY("");
        long start=System.nanoTime();
        long s=System.currentTimeMillis();//时间戳v
        Date d=new Date();
        System.out.println(start+"\t"+s);
        System.out.println(d);
    }


}
