package org;

/**
 * Created by sickle on 17-7-25.
 */

import java.util.Scanner;
import java.util.Stack;


class Node {
    Node leftChild;
    Node rightChild;
    String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    public Node(){}
    Node(String newData) {

        leftChild = null;
        rightChild = null;
        data = newData;
    }
}
public class Mycalc2 implements Runnable{
    Node node=new Node();
    static String strings=null;
    @Override
    public void run() {
            fenci(strings);
            jisuan(node);
    }
    public void creat(Node n,String[] s){
        n.leftChild=new Node(s[0]);
//        System.out.println(Thread.currentThread().getName());

        n.rightChild=new Node(s[1]);
//        System.out.println(Thread.currentThread().getName());

    }
    public void fenci(String string) {
        String[] temp=string.split("\\+",2);
        if(temp.length>=2) {
            jia(node, temp);
        }
        else {
            String[] temp1 = string.split("\\-", 2);
            if (temp1.length >= 2) {
                jian(node, temp);
            } else {
                String[] temp2 = string.split("\\*", 2);
                if (temp2.length >= 2) {
                    chen(node, temp);
                } else {
                    String[] temp3 = string.split("\\/", 2);
                    if (temp3.length >= 2) {
                        chu(node, temp);
                    }
                }
            }
        }

    }
    public void jia(Node n,String[] temp){
//        strings=temp;
        n.setData("+");
        creat(n,temp);
        String[] temp1 = temp[0].split("\\+", 2);
        String[] temp2 = temp[1].split("\\+", 2);
        if(temp1.length>=2){
            jia(n.leftChild,temp1);
        }
        else{
            String[] temp3 = temp[0].split("\\-", 2);
            if (temp3.length >= 2) {
                jian(n.leftChild,temp3);
            } else {
                String[] temp4 = temp[0].split("\\*", 2);
                if (temp4.length >= 2) {
                    chen(n.leftChild,temp4);
                } else {
                    String[] temp5 =  temp[0].split("\\/", 2);
                    if (temp3.length >= 2) {
                        chu(n.leftChild,temp5);
                    }
                }
            }
        }
        if(temp2.length>=2){
            jia(n.rightChild,temp2);
        }
        else{
            String[] temp3 = temp[1].split("\\-", 2);
            if (temp3.length >= 2) {
                jian(n.rightChild,temp3);
            } else {
                String[] temp4 = temp[1].split("\\*", 2);
                if (temp4.length >= 2) {
                    chen(n.rightChild,temp4);
                } else {
                    String[] temp5 =  temp[1].split("\\/", 2);
                    if (temp5.length >= 2) {
                        chu(n.rightChild,temp5);
                    }
                }
            }
        }
    }
    public void jian(Node n,String[] temp){
//        strings=temp;
        n.setData("-");
        creat(n,temp);
        String[] temp1 = temp[0].split("\\-", 2);
        String[] temp2 = temp[1].split("\\-", 2);
        if(temp1.length>=2){
            jian(n.leftChild,temp1);
        }
        else{
                String[] temp4 = temp[0].split("\\*", 2);
                if (temp4.length >= 2) {
                    chen(n.leftChild,temp4);
                } else {
                    String[] temp5 =  temp[0].split("\\/", 2);
                    if (temp5.length >= 2) {
                        chu(n.leftChild,temp5);
                    }
                }
        }
        if(temp2.length>=2){
            jian(n.rightChild,temp2);
        }
        else{
                String[] temp4 = temp[1].split("\\*", 2);
                if (temp4.length >= 2) {
                    chen(n.rightChild,temp4);
                } else {
                    String[] temp5 =  temp[1].split("\\/", 2);
                    if (temp5.length >= 2) {
                        chu(n.rightChild,temp5);
                    }
                }
        }


    }
    public void chen(Node n,String[] temp){
        n.setData("*");
        creat(n,temp);
        String[] temp1 = temp[0].split("\\*", 2);
        String[] temp2 = temp[1].split("\\*", 2);
        if(temp1.length>=2){
            jian(n.leftChild,temp1);
        }
        else{
                String[] temp5 =  temp[0].split("\\/", 2);
                if (temp5.length >= 2) {
                    chu(n.leftChild,temp5);
                }
        }
        if(temp2.length>=2){
            jian(n.rightChild,temp2);
        }
        else{

                String[] temp5 =  temp[1].split("\\/", 2);
                if (temp5.length >= 2) {
                    chu(n.rightChild,temp5);
                }

        }

    }
    public void chu(Node n,String[] temp){
        n.setData("/");
        creat(n,temp);
        String[] temp1=null;
        String[] temp2=null;
        if(temp.length>=2) {
            temp1=temp[0].split("\\/",2);
            temp2=temp[1].split("\\/",2);
        }
        if(temp1.length>=2){
            chu(n.leftChild,temp1);
        }

        if(temp2.length>=2){
            chu(n.rightChild,temp2);
        }


    }
    Stack<String> s1=new Stack<>();
    public void preOrderTraverse(Node node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        s1.push(node.data);
        preOrderTraverse(node.leftChild);

        preOrderTraverse(node.rightChild);

    }

    public void jisuan(Node n){
        if(n.rightChild!=null&&n.leftChild!=null){
            switch (n.getData()){
                case "+":
                    if(n.leftChild.getData().equals("+")||n.leftChild.getData().equals("-")||
                            n.leftChild.getData().equals("*")||n.leftChild.getData().equals("/")){
                        jisuan(n.leftChild);
                    }
                    if(n.rightChild.getData().equals("+")||n.rightChild.getData().equals("-")||
                            n.rightChild.getData().equals("*")||n.rightChild.getData().equals("/")){
                        jisuan(n.rightChild);
                    }
                        int s= Integer.parseInt(n.leftChild.getData())+Integer.parseInt(n.rightChild.getData());
                        n.setData(s+"");
                        jisuan(n);
                        break;
                case "-":
                    if(n.leftChild.getData().equals("+")||n.leftChild.getData().equals("-")||
                            n.leftChild.getData().equals("*")||n.leftChild.getData().equals("/")){
                        jisuan(n.leftChild);
                    }
                    else if(n.rightChild.getData().equals("+")||n.rightChild.getData().equals("-")||
                            n.rightChild.getData().equals("*")||n.rightChild.getData().equals("/")){
                        jisuan(n.rightChild);
                    }

                        int s1= Integer.parseInt(n.leftChild.getData())-Integer.parseInt(n.rightChild.getData());
                        n.setData(s1+"");
                        jisuan(n);

                    break;
                case "*":
                    if(n.leftChild.getData().equals("+")||n.leftChild.getData().equals("-")||
                            n.leftChild.getData().equals("*")||n.leftChild.getData().equals("/")){
                        jisuan(n.leftChild);
                    }
                    else if(n.rightChild.getData().equals("+")||n.rightChild.getData().equals("-")||
                            n.rightChild.getData().equals("*")||n.rightChild.getData().equals("/")){
                        jisuan(n.rightChild);
                    }

                        int s2= Integer.parseInt(n.leftChild.getData())*Integer.parseInt(n.rightChild.getData());
                        n.setData(s2+"");
                        jisuan(n);
                    break;
                case "/":
                    if(n.leftChild.getData().equals("+")||n.leftChild.getData().equals("-")||
                            n.leftChild.getData().equals("*")||n.leftChild.getData().equals("/")){
                        jisuan(n.leftChild);
                    }
                    else if(n.rightChild.getData().equals("+")||n.rightChild.getData().equals("-")||
                            n.rightChild.getData().equals("*")||n.rightChild.getData().equals("/")){
                        jisuan(n.rightChild);
                    }

                        int s3= Integer.parseInt(n.leftChild.getData())/Integer.parseInt(n.rightChild.getData());
                        n.setData(s3+"");
                        jisuan(n);
                    break;
                    default:
                        break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Scanner scanner=new Scanner(System.in);
        String string=scanner.nextLine();
        Mycalc2 my=new Mycalc2();
        Mycalc2.strings=string;
        Thread t1=new Thread(my,"aaa");
        t1.start();
        t1.join();
        Thread t2=new Thread(my,"bbb");
        t2.start();
        t2.join();
//        my.fenci(string);
//        my.preOrderTraverse(my.node);
//        System.out.println();
//        my.jisuan(my.node);
        System.out.println(my.node.getData());

    }


}
