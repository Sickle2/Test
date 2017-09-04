package UDPtest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

/**
 * Created by sickle on 17-9-4.
 */
public class TransFile implements Runnable {

    private SocketAddress socketAddress;

    private static final int DATA_LAN = 4096;


    byte[] bytes = new byte[DATA_LAN];

    private DatagramPacket inPacket = new DatagramPacket(bytes, bytes.length);

    private DatagramPacket outPacket;

    DatagramSocket socket = null;

    public TransFile(SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }

    @Override
    public void run() {
        byte[] buff = new byte[1024];

        File file = new File("newfile.txt");

        FileInputStream inputStream = null;
        try {
            socket = new DatagramSocket();

            inputStream = new FileInputStream(file);

            while (inputStream.read(buff) > 0) {
                outPacket = new DatagramPacket(buff,buff.length,socketAddress);

                outPacket.setData(buff);

                socket.send(outPacket);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
