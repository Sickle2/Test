package org;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by sickle on 17-7-22.
 */
public class Mycalc {
    ArrayList<String> ml=new ArrayList();
    public int add(int a,int b){
        return a+b;
    }
    public int sub(int a,int b){
        return a-b;
    }
    public int chen(int a,int b){
        return a*b;
    }
    public int chu(int a,int b){
        return a/b;
    }
    public void fen(String s){

        for (int i = 0; i < s.length(); i++) {
            String str = s.substring(i, i + 1);
                ml.add(str);                    //取出字符串的每个字符，存入ArrayList
        }
        suan();
    }
    public int suan() {
            for (int i = 0; i < ml.size(); i++) {
                if (ml.get(i).equals("*")) {
                    if(i-2>0) {                         //判断×号的位置
                        if (ml.get(i - 2).equals("/")) {    //前面有除法
                            int temp2 = chu(Integer.parseInt(ml.get(i - 3)), Integer.parseInt(ml.get(i - 1)));
                            int temp1 = chen(temp2, Integer.parseInt(ml.get(i + 1)));
                            ml.add(i - 3, temp1 + "");  //进行计算，存入数据
                            for (int j = 0; j < 5; j++) {
                                ml.remove(i - 2);   //删除无用数据
                            }
                            suan();         //递归调用自己，完成计算
                        }
                        else {              //前面没有除法，直接计算
                            int temp2 = chen(Integer.parseInt(ml.get(i - 1)), Integer.parseInt(ml.get(i + 1)));
                            ml.add(i - 1, temp2 + "");
                            for (int j = 0; j < 3; j++) {
                                ml.remove(i);
                                //   num--;
                            }
                            suan();
                        }

                    }
                    else {              //前面没有除法，直接计算
                            int temp2 = chen(Integer.parseInt(ml.get(i - 1)), Integer.parseInt(ml.get(i + 1)));
                            ml.add(i - 1, temp2 + "");
                            for (int j = 0; j < 3; j++) {
                                ml.remove(i);
                                //   num--;
                            }
                            suan();
                        }
                } else if (ml.get(i).equals("/")) {
                        int temp2 = chu(Integer.parseInt(ml.get(i - 1)), Integer.parseInt(ml.get(i + 1)));
                        ml.add(i - 1, temp2 + "");
                        for (int j = 0; j < 3; j++) {
                            ml.remove(i);
                           // num--;
                        }
                        suan();
                }
            }
            for (int i = 0; i < ml.size(); i++) {
                if (ml.get(i).equals("+")) {
                    int temp2 = add(Integer.parseInt(ml.get(i - 1)), Integer.parseInt(ml.get(i + 1)));
                    ml.add(i - 1, temp2 + "");
                    for (int j = 0; j < 3; j++) {
                        ml.remove(i);
                        //num--;
                    }
                    suan();
                } else {
                    if (ml.get(i).equals("-")) {
                        int temp2 = sub(Integer.parseInt(ml.get(i - 1)), Integer.parseInt(ml.get(i + 1)));
                        ml.add(i - 1, temp2 + "");
                        for (int j = 0; j < 3; j++) {
                            ml.remove(i);
                           // num--;
                        }
                        suan();
                    }
                }
            }

        return 0;
    }
    public static void main(String[] args) {
        Mycalc my=new Mycalc();
        Scanner sc=new Scanner(System.in);
        String ss=sc.nextLine();
        my.fen(ss);
         for(int i=0;i<my.ml.size();i++){
             System.out.println(my.ml.get(i));
         }
    }
}
