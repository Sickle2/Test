package org;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * Created by sickle on 17-7-27.
 */
class Node1 {
    Node1 leftChild;
    Node1 rightChild;
    String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    public Node1(){}
    Node1(String newData) {

        leftChild = null;
        rightChild = null;
        data = newData;
    }
}
class CreatNode extends RecursiveTask<Node1> {
    Node1 mnode;
    String str=null;

    public CreatNode(Node1 mnode,String[] str) {
        this.mnode = mnode;
        this.mnode.leftChild.data=str[0];
        this.mnode.leftChild.data=str[1];
    }

    @Override
    protected Node1 compute() {
        if(mnode==null){
            return null;
        }
        else {
            String[] temp=str.split("\\+",2);
            CreatNode c1=new CreatNode(mnode.leftChild,temp);
            c1.fork();
            CreatNode c2=new CreatNode(mnode.rightChild,temp);
            c2.fork();
            Node1 node = new Node1(mnode.data);
            node.leftChild = c1.join();
            node.rightChild = c2.join();
            return node;
        }
    }
}
public class MyCalcThread {
    public String string=null;
    public String[] str=null;
    Node1 node=new Node1();


    public static void main(String[] args) {

    }
}
