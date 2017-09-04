package UDPtest;

import ServerTest.Transfer;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by sickle on 17-8-31.
 */
public class Master implements Runnable{
    private static int NUM=3;

    private static final int DES_PORT=30000;

    private static final int DATA_LAN=1024;

    byte[] bytes = new byte[DATA_LAN];

    private DatagramPacket inPacket = new DatagramPacket(bytes,bytes.length);

    private DatagramPacket outPacket ;

    DatagramSocket socket = null;

    @Override
    public void run() {
        try {
            socket = new DatagramSocket();

            outPacket = new DatagramPacket("start".getBytes(),"start".getBytes().length,InetAddress.getByName("255.255.255.255"),DES_PORT);

            outPacket.setData("start".getBytes());
            socket.send(outPacket);

            socket.receive(inPacket);
            String string = new String(bytes,0,inPacket.getLength());
            if(NUM>0&(!string.equals("")|string!=null)){
                System.out.println(string);
                NUM--;
                TransFile t1 = new TransFile(inPacket.getSocketAddress());
                new Thread(t1).start();
                }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
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
