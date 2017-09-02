package UDPtest;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by sickle on 17-8-31.
 */
public class Master implements Runnable{
    private CountDownLatch theNum = new CountDownLatch(3);

    private static final int DES_PORT=30000;

    private static final int DATA_LAN=4096;

    private InetAddress broadcastAddress = null;

    byte[] bytes = new byte[DATA_LAN];

    private DatagramPacket inPacket = new DatagramPacket(bytes,bytes.length);

    private DatagramPacket outPacket ;

    DatagramSocket socket = null;

    @Override
    public void run() {
        try {
            socket = new DatagramSocket();

//            outPacket = new DatagramPacket(new byte[0],0,InetAddress.getByName("255.255.255.255"),DES_PORT);

            outPacket = new DatagramPacket("start".getBytes(),"start".getBytes().length,InetAddress.getByName("255.255.255.255"),DES_PORT);


//            while (true) {

                outPacket.setData("start".getBytes());
//                socket.receive(inPacket);

                String string = new String(bytes,0,inPacket.getLength());
                System.out.println(string);
//                if (!string.equals("")|string!=null){
                    byte[] buff = new byte[1024];

                    File file = new File("newfile.txt");

                    FileInputStream inputStream = new FileInputStream(file);

                    while (inputStream.read(buff)>0){
                        outPacket.setData(buff);

                        socket.send(outPacket);

                        socket.receive(inPacket);

                        System.out.println(new String(bytes, 0, inPacket.getLength()));
//                    }
//                }
//                else{
//                    System.out.println("error");
//                }
            }


        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        Master master = new Master();
        new Thread(master).start();
    }
}
