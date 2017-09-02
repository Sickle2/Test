package ServerTest.netty;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by sickle on 17-8-11.
 */
public class ClientInboundHandler  extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws InterruptedException {
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        String resultStr = new String(result1);
        result.release();
        System.out.println("Client said:" +"\n"+resultStr);

        String host=resultStr.split("Host: ",2)[1].split(":")[0];
        String port=resultStr.split("Host: ",2)[1].split(":")[1].split("\r\n")[0];
        System.out.println("The host is "+host);
        System.out.println("The port is "+port);

        EventLoopGroup group=new NioEventLoopGroup();

        Bootstrap bootstrap1=new Bootstrap();
        bootstrap1.group(group).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ServerInboundHandler());
                    }
                }).option(ChannelOption.TCP_NODELAY,true);

//        InetSocketAddress inetAddress=new InetSocketAddress("www.baidu.com",80);
        ChannelFuture channelFuture=bootstrap1.connect(host, Integer.parseInt(port)).sync();

        channelFuture.channel().closeFuture().sync();
        group.shutdownGracefully();


        ctx.write(msg);
    }
}