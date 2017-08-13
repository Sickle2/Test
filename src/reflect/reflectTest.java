package reflect;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by sickle on 17-8-12.
 */
public class reflectTest {
    public static void main(String[] args) {

        String classname="org.Mycalc2";
        try {
            Class c=Class.forName(classname);
            showFields(c);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void showFields(Class c){
        Field[] fields=c.getDeclaredFields();

        for(Field f:fields){
            String m= Modifier.toString(f.getModifiers());
            //修饰符  public

            Class type=f.getType();
            String t=type.getName();

            String name=f.getName();

            System.out.println(m+" " +  t+"    "+  name );
        }
    }
    /* 得到构造器 */
    public static void showConstructors(Class c) {
        Constructor[] cons = c.getDeclaredConstructors();
        for (Constructor con : cons) {
            String m = Modifier.toString(con.getModifiers());
            String n = con.getName();
            System.out.print(m + " " + n + "(");
            Class[] params = con.getParameterTypes();
            for (int j = 0; j < params.length; j++) {
                if (j == params.length - 1) {
                    System.out.print(params[j].getSimpleName());
                } else
                    System.out.print(params[j].getSimpleName() + ",");
            }
            System.out.println(")");
        }
    }

    /* 得到类里所有方法 */
    public static void showMethods(Class c) {
        Method[] m = c.getMethods();
        for (int i = 0; i < m.length; i++) {
			/* 方法修饰符 */
            String modify = Modifier.toString(m[i].getModifiers());
            System.out.print(modify + " ");
			/* 方法返回类型 */
            Class returntype = m[i].getReturnType();
            System.out.print(returntype.getName() + " ");
			/* 方法名称 */
            String name = m[i].getName();
            System.out.print(name + "(");
			/* 方法参数 */
            Class[] params = m[i].getParameterTypes();
            for (int j = 0; j < params.length; j++) {
                if (j == params.length - 1) {
                    System.out.print(params[j].getSimpleName());
                } else
                    System.out.print(params[j].getSimpleName() + ",");
            }
            System.out.println(")");

        }

    }
}
