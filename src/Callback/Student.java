package Callback;

/**
 * Created by sickle on 17-8-18.
 */
public class Student implements doHomework{

    @Override
    public void dohomework( String question,String answer) {
        if(question!=null){
            System.out.println("问题："+question+"答案："+answer);
        }
        else{
            System.out.println(".........");
        }
    }

    public void ask(String question,Classmate classmate){
        classmate.helpClassmate(question,Student.this);
    }

    public static void main(String[] args) {
        Student student=new Student();
        String homework="1+1";
        student.ask(homework,new Classmate());
    }
}
