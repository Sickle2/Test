package UDPtest;

import io.netty.buffer.ByteBuf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Created by sickle on 17-8-31.
 */
public class Slave4 implements Runnable{

    private static final int DES_PORT=30003;

    private static final int DATA_LAN=1024;

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

            socket.receive(inPacket);

            string = new String(bytes,0,inPacket.getLength());
            System.out.println(string);
            if (string.equals("start")){

                byte[] buff = "4".getBytes();

                outPacket = new DatagramPacket(buff,buff.length,inPacket.getSocketAddress());

                socket.send(outPacket);
                socket.receive(inPacket);
                System.out.println(new String(bytes,0,inPacket.getLength()));
                File file = new File("4.txt");
                FileChannel fileChannel = new FileOutputStream(file).getChannel();

                ByteBuffer buffer = ByteBuffer.allocateDirect(inPacket.getLength());
                buffer.put(inPacket.getData());
                buffer.flip();
                fileChannel.write(buffer);

                fileChannel.close();

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
