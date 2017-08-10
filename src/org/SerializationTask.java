package org;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by sickle on 17-7-30.
 */
public class SerializationTask {



    public void doSerialization(Object object) throws ClassNotFoundException, IllegalAccessException, IOException {
        Field[]  f,ff1; //获取该类的字段（public, protected, default (package) access, and private）
        f = object.getClass().getDeclaredFields();
        ArrayList<String> arr=new ArrayList<>();
        Class clazz=object.getClass().getSuperclass();
        ff1=clazz.getDeclaredFields();
        for(Field ff:ff1)            //遍历字段
        {
            arr.add(ff.getType().toString());
            arr.add(ff.getName().toString());
//            System.out.println(ff.getType().toString());    //获取字段类型
//            System.out.println(ff.getName().toString());    //获取字段名
            // 获取原来的访问控制权限
            boolean accessFlag = ff.isAccessible();
            // 修改访问控制权限
            ff.setAccessible(true);
            Object ob=ff.get(object);
            arr.add(ob.toString());
//            System.out.println(ob.toString());
            ff.setAccessible(accessFlag);
        }
        for(Field ff:f)            //遍历字段
        {
            arr.add(ff.getType().toString());
            arr.add(ff.getName().toString());
//            System.out.println(ff.getType());    //获取字段类型
//            System.out.println(ff.getName());    //获取字段名
            // 获取原来的访问控制权限
            boolean accessFlag = ff.isAccessible();
            // 修改访问控制权限
            ff.setAccessible(true);
            Object ob=ff.get(object);
            arr.add(ob.toString());
//            System.out.println(ob);
            ff.setAccessible(accessFlag);
        }
        doString(arr);
    }
    public void doString(ArrayList<String> arr) throws IOException {
        File file=new File("/home/sickle/totest.txt");
        FileWriter fw=new FileWriter(file,true);
        if(file.length()>0){
            fw.write(",");
        }
        fw.write("{");
        for(int i=0;i<arr.size();i=i+3){
            if(arr.get(i).equals("int")){
                fw.write("\"");
                fw.write(arr.get(i+1));
                fw.write("\"");
                fw.write(":");
                fw.write(arr.get(i+2));
                if(i+3==arr.size()){

                }else
                    fw.write(",");
            }
            else{
                fw.write("\"");
                fw.write(arr.get(i+1));
                fw.write("\"");
                fw.write(":");
                fw.write("\"");
                fw.write(arr.get(i+2));
                fw.write("\"");
                if(i+3==arr.size()){

                }else
                    fw.write(",");
            }
        }
        fw.write("}");
        fw.flush();
        fw.close();
        System.out.println("");
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, IOException {
        SerializationTask ser=new SerializationTask();
        Student person=new Student("xxx","男",18,"123123123","asdfasd");
        Person student=new Person("xxx","男",20);
        ser.doSerialization(person);
        ser.doSerialization(student);
//        System.out.println(arr.size());
    }
}