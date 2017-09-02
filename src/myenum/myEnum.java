package myenum;

/**
 * Created by sickle on 17-8-31.
 */
public enum  myEnum {
    PLUS{
        public double eval(double x,double y){
            return x+y;
        }
    },
    TIMES{
        @Override
        public double eval(double x, double y) {
            return x*y;
        }

    };

    public abstract double eval(double x,double y);

    public static void main(String[] args) {
        System.out.println(myEnum.PLUS.eval(3,5));
        System.out.println(myEnum.TIMES.eval(4,5));
        myEnum myEnum= myenum.myEnum.valueOf("PLUS");
    }

}
