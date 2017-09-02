package ServerTest;

import com.sun.prism.GraphicsPipeline;
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
//        while (true) {
            try {
                s = new Socket("127.0.0.1", 30000);
                System.out.println(s.getLocalPort());
                out = new PrintStream(s.getOutputStream());
//                Thread.sleep(1000);
                out.println("ServerTest.People|toString");
                out.flush();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));
                // 进行普通IO操作
                String line = br.readLine();
                System.out.println("来自服务器的数据：" + line);
                // 关闭输入流、socket
                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {

                    out.close();
            }

        }
//    }
}
