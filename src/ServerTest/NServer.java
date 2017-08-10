package ServerTest;

/**
 * Created by sickle on 17-8-7.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;


public class NServer {

    private Selector selector=null;
    static final int PORT=30000;

    private Charset charset=Charset.forName("utf-8");

    public void init() throws IOException {
        selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();


        InetSocketAddress inetSocketAddress = new InetSocketAddress(PORT);

        serverSocketChannel.socket().bind(inetSocketAddress);

        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        String content = "";
        String path1 = null;
        while (selector.select() > 0) {

            for (SelectionKey sk : selector.selectedKeys()) {

                selector.selectedKeys().remove(sk);
                if (sk.isAcceptable()) {
                    ServerSocketChannel ss = (ServerSocketChannel) sk.channel();

                    SocketChannel socketChannel = ss.accept();

                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);
//                    sk.interestOps(SelectionKey.OP_READ);
                    // socketChannel.register(selector,SelectionKey.OP_WRITE);
//                    sk.interestOps(SelectionKey.OP_ACCEPT);
                    //selector.selectedKeys().remove(sk);


                }

                if (sk.isReadable()) {

                    SocketChannel socketChannel = (SocketChannel) sk.channel();

                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    if (socketChannel.read(buffer) > 0) {
                        buffer.flip();
                        content += charset.decode(buffer);
                        buffer.clear();
                    }
                    else
                        socketChannel.close();
                    //buffer.clear();
                    //socketChannel.register(selector,SelectionKey.OP_WRITE);
//                    socketChannel.configureBlocking(false);
//                    socketChannel.register(selector,SelectionKey.OP_WRITE);
                    System.out.println(content);

                    String tempString = content.substring(4);
                    String[] path = tempString.split(" HTTP");
                    System.out.println(content);
                    System.out.println(path[0]);
                    path1 = path[0];
//                    sk.interestOps(SelectionKey.OP_READ);
                    //selector.selectedKeys().remove(sk);
                    content="";
                    //使用完content清理

                    sk.interestOps(SelectionKey.OP_WRITE);


                }
                    if (sk.isWritable()) {
                        SocketChannel sc = (SocketChannel) sk.channel();


                        File f = new File(path1);

                        FileChannel file = new FileInputStream(f).getChannel();

                        MappedByteBuffer buff = file.map(FileChannel.MapMode.READ_ONLY, 0, f.length());

                        ByteBuffer httpResq = charset.encode("HTTP/1.1 200 OK \r\nContent-Type:text/html;charset:utf-8\r\n" +
                                "Content-Length:" + f.length() + "\r\n" + "\r\n");
                        sc.write(httpResq);
                        //buff.flip();
                        sc.write(buff);
                        file.close();

//                    sc.configureBlocking(false);
                    sc.register(selector,SelectionKey.OP_READ);
//                        sk.interestOps(SelectionKey.OP_READ);
                        sc.close();
//                    sc.register(selector,SelectionKey.OP_ACCEPT);
                    }
                }
            }

        }

    public static void main(String[] args) throws IOException {
        new NServer().init();
    }
}