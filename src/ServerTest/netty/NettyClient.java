package ServerTest.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import static ServerTest.netty.ServerInboundHandler.resultStr;

/**
 * Created by sickle on 17-8-25.
 */
public class NettyClient {
    public void strart(String host,int port) throws InterruptedException {


        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap boot = new Bootstrap();
        Bootstrap bootstrap1 = new Bootstrap();
        bootstrap1.group(group).

                channel(NioSocketChannel.class)
                .

                        handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                socketChannel.pipeline().addLast(new ServerInboundHandler());
                            }
                        }).

                option(ChannelOption.TCP_NODELAY, true);

        //        InetSocketAddress inetAddress=new InetSocketAddress("www.baidu.com",80);
        ChannelFuture channelFuture = bootstrap1.connect(host, port).sync();

        channelFuture.channel().closeFuture().sync();
        group.shutdownGracefully();
    }

    public static void main(String[] args) throws InterruptedException {
        NettyClient nettyClient=new NettyClient();
        nettyClient.strart("127.0.0.1",30000);
    }
}
