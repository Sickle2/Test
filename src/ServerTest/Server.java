package ServerTest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by sickle on 17-8-2.
 */
public class Server implements Runnable {
    ServerSocket ss=null;

    public Server() throws IOException {
        ss=new ServerSocket(30001);
    }

    public static void main(String[] args) throws IOException {
        new Thread(new Server()).start();
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("listenning.....");
            try {
                Socket s = ss.accept();
//                ServerThread st = new ServerThread(s);
                sendHtml sh=new sendHtml(s);
                Thread t = new Thread(sh);
                t.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
