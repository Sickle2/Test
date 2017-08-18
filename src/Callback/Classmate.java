package Callback;

/**
 * Created by sickle on 17-8-18.
 */
public class Classmate {
    public void helpClassmate(String homeword,doHomework student){
        if(homeword.equals("1+1")){
            student.dohomework(homeword,"2");
        }
        else {
            student.dohomework(homeword,"   不会");
        }

    }

    public static void main(String[] args) {
        Classmate classmate=new Classmate();
        classmate.helpClassmate("1+1", new doHomework() {
            @Override
            public void dohomework(String answer, String question) {
                System.out.println("问题："+answer+"答案："+question);
            }
        });
    }
}
