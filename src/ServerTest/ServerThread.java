package ServerTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by sickle on 17-8-2.
 */
public class ServerThread implements Runnable{
    private Socket s=null;

    public ServerThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        BufferedReader buff= null;
        String line= null;
        try {
            buff = new BufferedReader(new InputStreamReader(s.getInputStream()));
            line = buff.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("客户端收到："+line);
    }
}
