package ServerTest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by sickle on 17-8-8.
 */
public class PortMap implements Runnable{
    ServerSocket serverSocket=new ServerSocket(30000);

    public PortMap() throws IOException {
    }


    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Listenning..............");
                Socket socket = serverSocket.accept();
                new Transfer(socket).run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(new PortMap()).start();;
    }
}
