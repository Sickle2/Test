package ServerTest.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerInvoker;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.concurrent.EventExecutorGroup;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by sickle on 17-8-11.
 */
public class HttpServerInboundHandler extends ChannelInboundHandlerAdapter {
    private HttpRequest request;
    private String uri ="";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {

        try {
            FullHttpRequest fhr = (FullHttpRequest) msg;

//            ByteBuf buf = fhr.content();
//            String message = buf.toString(io.netty.util.CharsetUtil.UTF_8);
//            buf.release();
            String tt = HttpClientInboundHandler.message;
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                    OK, Unpooled.wrappedBuffer(tt.getBytes("UTF-8")));
            response.headers().set(CONTENT_TYPE, "text/html");
            response.headers().setInt(CONTENT_LENGTH,
                    response.content().readableBytes());
//            response.content().setBytes(1,buf);
            if (HttpHeaderUtil.isKeepAlive(fhr)) {
                response.headers().set(CONNECTION, KEEP_ALIVE);
            }
//            response.content().writeBytes(ByteBuffer.wrap("asdfasdfasdfasdfasdfasdf".getBytes()));
            ctx.write(response);
            ctx.flush();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
