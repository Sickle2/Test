package ServerTest.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * Created by sickle on 17-8-24.
 */
public class outHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        String string=(String)msg;
        ByteBuf encoded = ctx.alloc().buffer(4 * string.length());
        encoded.writeBytes(string.getBytes());
        ctx.write(encoded);
        ctx.flush();
//        super.write(ctx, msg, promise);
    }
}
