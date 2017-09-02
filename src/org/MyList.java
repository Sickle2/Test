package org;

import java.util.Arrays;



/**
 * Created by sickle on 17-7-21.
 */
public class MyList <T>{
    static int size=2;
    static int no=0;
    Object[] arr;
    public int getSize() {
        return size;
    }
    public MyList(){
        arr=new Object[size];
        arr=Arrays.copyOf(arr,arr.length,Object[].class);
    }

    public void grow(){
//        int oldCapacity = arr.length;
//        int newCapacity = oldCapacity + (oldCapacity >> 1);
//        if (newCapacity - minCapacity < 0)
//            newCapacity = minCapacity;
            arr= Arrays.copyOf(arr,(size>>1)+size );
            size=(size>>1)+size;
    }
    public void add(T a){
        if(no>size/2){
            grow();
        }
        arr[no] = a;
        no++;
    }
    public T get(int x){
        return (T) arr[x];
    }
    public void gai(int i,T a){
        arr[i]=a;
    }
    public void rm(int i){
        int numMoved = size - i - 1;
        if (numMoved > 0) {
            System.arraycopy(arr, i + 1, arr, i, numMoved);
        }
    }
}
