package ServerTest.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.EventExecutorGroup;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by sickle on 17-8-24.
 */
public class inHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws InterruptedException, ClassNotFoundException {
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        String resultStr = new String(result1);
        result.release();
        System.out.println(resultStr);
        String classname=resultStr.split("|")[0];
        String m=resultStr.split("|")[1];
        ClassLoader classLoader=ClassLoader.getSystemClassLoader();
        Class c=classLoader.loadClass("ServerTest.People");
        String ss="";
        if(c!=null){
            Object object= null;
            try {
                object = c.newInstance();

                Method method=c.getMethod("toString");
                 ss= (String) method.invoke(object,null);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        msg=ss;
        ctx.write(msg);
    }
}
