package ServerTest.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by sickle on 17-8-10.
 */
public class nettyPortMap {
    public void start(int port) throws InterruptedException {

        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup worker=new NioEventLoopGroup();

        ServerBootstrap bootstrap=new ServerBootstrap();
        bootstrap.group(boss,worker).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new ClientOutboundHandler());
                        channel.pipeline().addLast(new ClientInboundHandler());
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
