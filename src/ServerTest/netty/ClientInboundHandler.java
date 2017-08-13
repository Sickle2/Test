package ServerTest.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerInvoker;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by sickle on 17-8-11.
 */
public class ClientInboundHandler  extends ChannelInboundHandlerAdapter {

    private HttpRequest request;
    private String uri ="";
    static String message;


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {

        try {
            FullHttpResponse response=(FullHttpResponse) msg;

//               HttpResponse response=(HttpResponse) msg;
               ByteBuf content=response.content();

               message = content.toString(CharsetUtil.UTF_8);
               System.out.println(message);
               ctx.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
