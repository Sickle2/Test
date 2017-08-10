package org;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

/**
 *
 * @author Dao
 */
public class SpiderTest {

    public SpiderTest() {
    }

    public static void main(String args[])
    {
        //你想获取代码的网站
        String strServer = "www.baidu.com";
        //起始页面，/为根页
        String strPage = "/";

        try
        {
            //设置端口，通常http端口不就是80罗，你在地址栏上没输就是这个值
            int port = 80;
            //用域名反向获得IP地址
            InetAddress addr = InetAddress.getByName(strServer);

            //建立一个Socket
            Socket socket = new Socket(addr, port);

            //发送命令,无非就是在Socket发送流的基础上加多一些握手信息，详情请了解HTTP协议
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            wr.write("GET " + strPage + " HTTP/1.1\r\n");
            wr.write("HOST:" + strServer + "\r\n");
//            wr.write("Accept:*/*\r\n");
            wr.write("\r\n");
            wr.flush();

            //接收Socket返回的结果,并打印出来
            BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            FileWriter file=new FileWriter("/home/sickle/test.html");
            String line;
            while ((line = rd.readLine()) != null)
            {
                file.write(line);
                System.out.println(line);

            }
            wr.close();
            rd.close();
            file.close();
            socket.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}