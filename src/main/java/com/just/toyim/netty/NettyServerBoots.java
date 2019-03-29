package com.just.toyim.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class NettyServerBoots implements ApplicationListener<ContextRefreshedEvent> {

    private final int WS_PORT = 2333;

    @Autowired
    NettyWSServer nettyWSServer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // start netty server during initializing spring context
        nettyWSServer.start(WS_PORT);
    }
}
