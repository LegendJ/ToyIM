package com.just.toyim.netty.handler;

import com.just.toyim.service.IMService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
@ChannelHandler.Sharable
public class WSIdleHandler extends IdleStateHandler {

    @Autowired
    IMService imService;

    private static final int READER_IDLE_TIME = 15;

    public WSIdleHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.MINUTES);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        System.out.println(READER_IDLE_TIME + "秒内未读到数据，关闭连接");
        ctx.channel().close();
        imService.remove(ctx);
    }
}
