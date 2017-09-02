package UDPtest;

import java.io.FileWriter;
import java.io.IOException;
import java.net.*;

/**
 * Created by sickle on 17-8-31.
 */
public class Slave1 implements Runnable{

    private static final int DES_PORT=30000;

    private static final int DATA_LAN=4096;

    private InetAddress broadcastAddress = null;

    byte[] bytes = new byte[DATA_LAN];

    private DatagramPacket inPacket = new DatagramPacket(bytes,bytes.length);

    private DatagramPacket outPacket ;

    private DatagramSocket socket ;

    @Override
    public void run() {
        try {
            socket = new DatagramSocket(DES_PORT);

            String string = null;
            while (true){
                socket.receive(inPacket);

                string = new String(bytes,0,inPacket.getLength());
//                System.out.println(string);
                System.out.println(new String(bytes, 0, inPacket.getLength()));
//                if(string.equals("start")){
                    byte[] buff = "1".getBytes();

                    outPacket = new DatagramPacket(buff,buff.length,inPacket.getSocketAddress());

                    socket.send(outPacket);
//                }


            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Slave1 slave1 = new Slave1();
        new Thread(slave1).start();
    }
}
