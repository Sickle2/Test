package ServerTest.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpChannel;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * Created by sickle on 17-8-10.
 */
public class nettyPortMap {
    public void start(int port) throws InterruptedException {

        EventLoopGroup group=new NioEventLoopGroup();

        Bootstrap bootstrap1=new Bootstrap();
        bootstrap1.group(group).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new HttpResponseDecoder());
                        // server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码
                        socketChannel.pipeline().addLast(new HttpRequestEncoder());
                        socketChannel.pipeline().addLast(new HttpObjectAggregator(65535));
                        socketChannel.pipeline().addLast(new ChunkedWriteHandler());
                        socketChannel.pipeline().addLast(new HttpClientInboundHandler());
                    }
                }).option(ChannelOption.TCP_NODELAY,true);

        InetSocketAddress inetAddress=new InetSocketAddress("www.baidu.com",80);
        ChannelFuture channelFuture=bootstrap1.connect(inetAddress).sync();

        DefaultFullHttpRequest request=new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,HttpMethod.GET,"/");
        request.headers().set(HttpHeaderNames.HOST,"www.baidu.com");
        request.headers().set(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE);
//        request.headers().set(HttpHeaderNames.CONTENT_LENGTH,request.content().readableBytes());
//        request.headers().set("messageType","normal");
        channelFuture.channel().write(request);
        channelFuture.channel().flush();


        channelFuture.channel().closeFuture().sync();

        group.shutdownGracefully();

        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup worker=new NioEventLoopGroup();

        ServerBootstrap bootstrap=new ServerBootstrap();
        bootstrap.group(boss,worker).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new HttpResponseEncoder());
                        // server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码
                        channel.pipeline().addLast(new HttpRequestDecoder());
                        channel.pipeline().addLast(new HttpObjectAggregator(65535));
                        channel.pipeline().addLast(new ChunkedWriteHandler());
                        channel.pipeline().addLast(new HttpServerInboundHandler());
//                       channel.pipeline().addLast(new OutboundHander);
                    }
                }).option(ChannelOption.SO_BACKLOG,128).childOption(ChannelOption.SO_KEEPALIVE,true);

        ChannelFuture cf=bootstrap.bind(port).sync();

        cf.channel().closeFuture().sync();

        boss.shutdownGracefully();
        worker.shutdownGracefully();
    }

    public static void main(String[] args) throws InterruptedException {
        nettyPortMap n=new nettyPortMap();
        n.start(30000);
    }


}
