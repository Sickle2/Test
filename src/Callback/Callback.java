package Callback;

/**
 * Created by sickle on 17-8-18.
 */
interface test{
    void callback(String s,String s1);
}
//class test1 implements test{
//
//    @Override
//    public void callback(String s,String s1) {
//        System.out.println(s+s1);
//    }
//}
public class Callback {
    public void test(test test){
        test.callback("........","*************");
    }
    public static void main(String[] args) {
        Callback callback=new Callback();
        callback.test(new test() {
            @Override
            public void callback(String s, String s1) {
                System.out.println(s);
            }
        });
    }
}
