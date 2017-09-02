package ServerTest.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerDomainSocketChannel;
import io.netty.channel.epoll.EpollServerSocketChannel;

import java.awt.*;

/**
 * Created by sickle on 17-8-24.
 */
public class RPC {
    public void start(int port){
        EventLoopGroup boot =new EpollEventLoopGroup();
        EventLoopGroup work =new EpollEventLoopGroup();
        ServerBootstrap bootstrap=new ServerBootstrap();
        bootstrap.group(boot,work).channel(EpollServerSocketChannel.class)
                .childHandler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new outHandler());
                        channel.pipeline().addLast(new inHandler());
                    }
                }).childOption(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.SO_BACKLOG,128);
        try {
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();

            boot.shutdownGracefully();
            work.shutdownGracefully();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RPC rpc=new RPC();
        rpc.start(30000);
    }
}
