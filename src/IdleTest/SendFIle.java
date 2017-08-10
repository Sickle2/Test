package IdleTest;

/**
 * Created by sickle on 17-8-2.
 */
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 文件发送客户端主程序
 * @author admin_Hzw
 *
 */
public class SendFIle {

    /**
     * 程序main方法
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        int length = 0;
        double sumL = 0 ;
        double sumLt=0;
        byte[] sendBytes = null;
        Socket socket = null;
        OutputStream dos = null;
        FileInputStream fis = null;
        FileOutputStream out=null;
        boolean bool = false;
        try {
            File file = new File("/home/sickle/navicat112_premium_cs_x64.tar.gz"); //要传输的文件路径
            long l = file.length();
            File file1 = new File("/home/sickle/sss/temp");

            socket = new Socket();
            socket.connect(new InetSocketAddress("127.0.0.1", 48123));
            dos = socket.getOutputStream();
            fis = new FileInputStream(file);
            sendBytes = new byte[1024];
            if(file1.exists()){
                out=new FileOutputStream(file1,true);
                while ((length = fis.read(sendBytes)) > 0) {
                    sumLt =length+sumLt;
                    if (sumLt < file1.length()) {
                        ;
                    } else {
                        System.out.println("已传输：" + ((sumLt / l) * 100) + "%"); 
                        dos.write(sendBytes, 0, length);
                        dos.flush();
                        out.write(sendBytes, 0, length);
                        out.flush();
                    }

                }
            }
            else {
                out=new FileOutputStream(file1);
                while ((length = fis.read(sendBytes)) > 0) {
                    sumL =length+sumL;
                    System.out.println("已传输：" + ((sumL / l) * 100) + "%");
                    dos.write(sendBytes, 0, length);
                    dos.flush();
                    out.write(sendBytes,0,length);
                    out.flush();
                }

            }
            out.close();
            System.out.println(sumL+"    "+l);
            System.out.println(sumLt+"       11    "+l);
            //虽然数据类型不同，但JAVA会自动转换成相同数据类型后在做比较
            if(sumL==l||sumLt==l){
                bool = true;
            }
            file1.deleteOnExit();
        }catch (Exception e) {
            System.out.println("客户端文件传输异常");
            bool = false;
            e.printStackTrace();
        } finally{
            if (dos != null)
                dos.close();
            if (fis != null)
                fis.close();
            if (socket != null)
                socket.close();
        }
        System.out.println(bool?"成功":"失败");
    }
}