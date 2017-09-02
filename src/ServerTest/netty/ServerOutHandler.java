package ServerTest.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * Created by sickle on 17-8-25.
 */
public class ServerOutHandler  extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ctx.write("ServerTest.People|toString");
        ctx.flush();
//        super.write(ctx, msg, promise);
    }
}
