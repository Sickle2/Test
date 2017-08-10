package ServerTest;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.io.*;
import java.net.Socket;

/**
 * Created by sickle on 17-8-2.
 */
public class Client implements Runnable{
    Socket s= null;
    PrintStream out=null;
    public static void main(String[] args) throws IOException {
        new Thread(new Client()).start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                s = new Socket("127.0.0.1", 30001);
                System.out.println(s.getLocalPort());
                out = new PrintStream(s.getOutputStream());
                Thread.sleep(1000);
                out.println("...........................");
                out.close();
                s.close();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
