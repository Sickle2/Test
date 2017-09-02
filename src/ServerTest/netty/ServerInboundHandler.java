package ServerTest.netty;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;


/**
 * Created by sickle on 17-8-11.
 */
public class ServerInboundHandler extends ChannelInboundHandlerAdapter {
    private String string;
    static String resultStr;
//    public ServerInboundHandler(String s) {
//        string=s;
//    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        resultStr = new String(result1);
        result.release();
        System.out.println("Server said:" +"\n"+resultStr);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ByteBuf encoded = ctx.alloc().buffer(4 * "ServerTest.People|toString".length());
        encoded.writeBytes("ServerTest.People|toString".getBytes());
        ctx.write(encoded);
        ctx.flush();
    }
}
