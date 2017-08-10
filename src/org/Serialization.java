package org;

import java.io.*;

/**
 * Created by sickle on 17-7-29.
 */
class Person  implements Serializable {
    private String name;
    private String sex;
    private int age;

    public Person(){}

    public Person(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "["+"\"name:\""+name+"\"sex:\""+sex+"\"age:\""+age+"]";
    }
}
class Student extends Person  implements Serializable {
    private String id;
    private String school;

    public Student() {}

    public Student(String name, String sex, int age, String id, String school) {
        super(name, sex, age);
        this.id = id;
        this.school = school;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "["+"\"id:\""+id+"\"school:\""+school+super.toString()+"]";
    }
}
public class Serialization{
    public void doSerialization(Object obj) throws IOException {
        File file=new File("ser.txt");
        FileOutputStream fw = new FileOutputStream(file);
        ObjectOutputStream oops=new ObjectOutputStream(fw);
        oops.writeObject(obj);
        System.out.println("序列化成功！！");
        oops.close();
    }
    public Student DeserializePerson() throws Exception, IOException {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                                 new File("ser.txt")));
                Student student = (Student) ois.readObject();
                 System.out.println("对象反序列化成功！");

                 return student;
             }
    public static void main(String[] args) throws Exception {
        Serialization serialization=new Serialization();
        Student st=new Student("xxx","男",20,"123123","xxx");
        serialization.doSerialization(st);
        Student sss=serialization.DeserializePerson();
        System.out.println(sss.toString());
    }
}
