package ServerTest;

import java.io.*;
import java.net.Socket;

/**
 * Created by sickle on 17-8-6.
 */
public class sendHtml implements Runnable{

    private Socket s=null;

    public sendHtml(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            System.out.println(".................");
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String string = br.readLine();
            System.out.println("将要传输： " + string);
            String tem = string.substring(4);
            String[] tem1 = tem.split(" HTTP/1.1");
            System.out.println(tem);
            System.out.println(tem1[0]);
            PrintStream bw = new PrintStream(s.getOutputStream());
            File file=new File(tem1[0]);
            bw.println("HTTP/1.1 200 OK");
            bw.println("Content-Type:text/html;charset:utf-8");
            bw.println("Content-Length:"+file.length());// 返回内容字节数
            bw.println();// 根据 HTTP 协议, 空行将结束头信息
            bw.flush();
            if (tem1[0].equals("/favicon.ico")) {
                    bw.println("444");
                    bw.flush();
            } else {

                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String st = null;
                while ((st = bufferedReader.readLine()) != null) {
                    System.out.println(st);
                    bw.println(st);
                    //http://news.sohu.com/ bw.println("\n");
                    bw.flush();
                    //bw.close();
                }

                if (bw != null)
                    bw.close();
                if (bufferedReader != null)
                    bufferedReader.close();
                if (s != null)
                    s.close();
            }
        }catch(IOException e){
                e.printStackTrace();
            }

    }
}
