import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static int res(ArrayList<Integer> list,int num){
        int temp=0;
        for(int i=0;i<list.size()-1;i++){
            for (int j=0;j<list.size()-1;j++){
                if (list.get(j)<list.get(j+1)){
                    temp=list.get(j);
                    list.set(j,list.get(j+1));
                    list.set(j+1,temp);
                }
            }

        }
        for (int j=0;j<list.size();j++){
            System.out.println(list.get(j));
        }
        return list.get(num-1);
    }
    public static void main(String[] args) {
        int num;
        ArrayList<Integer> list = new ArrayList();
        Scanner in = new Scanner(System.in);
        num=in.nextInt();
        int max=in.nextInt();
        for(int i=0;i<max;i++) {
            list.add(in.nextInt());
        }
        System.out.println(Main.res(list,num));
    }
}
