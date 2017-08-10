package IdleTest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;


/**
 * 接收文件服务
 * @author admin_Hzw
 *
 */
public class TakeFile {

    /**
     * 工程main方法
     * @param args
     */
    public static void main(String[] args) {
        try {
            final ServerSocket server = new ServerSocket(48123);
            Thread th = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            System.out.println("开始监听...");
                            /*
                             * 如果没有访问它会自动等待
                             */
                            Socket socket = server.accept();
                            System.out.println("有链接");
                            receiveFile(socket);
                        } catch (Exception e) {
                            System.out.println("服务器异常");
                            e.printStackTrace();
                        }
                    }
                }
            });
            th.run(); //启动线程运行
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
    }

    /**
     * 接收文件方法
     * @param socket
     * @throws IOException
     */
    public static void receiveFile(Socket socket) throws IOException {
        byte[] inputByte = null;
        int length = 0;
        InputStream dis = null;
        FileOutputStream fos = null;
        dis = socket.getInputStream();
        String filePath = "/home/sickle/sss/"+"chuan.tar.gz";
        System.out.println("开始接收:   "+"chuan.tar.gz");
        try {
            try {

                File f = new File("/home/sickle/sss");
                if(!f.exists()){
                    f.mkdir();
                }
                /*
                 * 文件存储位置
                 */
                fos = new FileOutputStream(new File(filePath),true);
                inputByte = new byte[1024];
                System.out.println("开始接收数据...");
                while ((length = dis.read(inputByte, 0, inputByte.length)) > 0) {
                    fos.write(inputByte, 0, length);
                    fos.flush();
                }
                System.out.println("完成接收："+filePath);
            } finally {
                if (fos != null)
                    fos.close();
                if (dis != null)
                    dis.close();
                if (socket != null)
                    socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}