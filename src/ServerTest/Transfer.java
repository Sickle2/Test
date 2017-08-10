package ServerTest;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by sickle on 17-8-9.
 */
public class Transfer implements Runnable{
    String strServer = "www.baidu.com";
    //起始页面，/为根页
    String strPage = "/";
    Socket client=null;
    public Transfer(Socket socket) {
        this.client=socket;
    }
    @Override
    public void run() {
        BufferedWriter server = null;
        PrintStream outClient = null;
        BufferedReader serbuff= null;;
        String HttpString=null;

        String temp=null;
        try {
            InetAddress address=InetAddress.getByName(strServer);
            Socket s=new Socket(address,80);;
            BufferedReader buff=new BufferedReader(new InputStreamReader(client.getInputStream()));
            outClient=new PrintStream(client.getOutputStream());
            outClient.println("HTTP/1.1 200 OK");
            outClient.println("Content-Type:text/html;charset=utf-8");
//            outClient.println("Content-Length:"+HttpString.length());// 返回内容字节数
            outClient.println();// 根据 HTTP 协议, 空行将结束头信息
            outClient.flush();
//            do {
//                System.out.println(temp+"\r\n");
//            }while((temp=buff.readLine())!=null);
            //buff.close();
            server=new BufferedWriter(new OutputStreamWriter(s.getOutputStream(),"UTF-8"));

            serbuff=new BufferedReader(new InputStreamReader(s.getInputStream()));

            server.write("GET "+strPage+" HTTP/1.1"+"\r\n");
            server.write("HOST:"+strServer+"\r\n");
//            printStream.write("Connection: keep-alive");
            server.write("\r\n");
            server.flush();
            String str=null;
            while ((str=serbuff.readLine())!=null){
//                System.out.println(HttpString);

                if(str.split("<",2).length>1){
                    outClient.println(str);
                    outClient.flush();
                }

//                HttpString+=str;
            }

//            String strings=HttpString.split("<",2)[1];



            /*if(server!=null)
                server.close();

            if(outClient!=null)
                outClient.close();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
