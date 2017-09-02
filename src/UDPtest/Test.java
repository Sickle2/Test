package UDPtest;

/**
 * Created by sickle on 17-8-31.
 */
public class Test {
    public static void main(String[] args) {
        Master master = new Master();
        new Thread(master).start();

        Slave1 slave1 = new Slave1();

        new Thread(slave1).start();
    }
}
