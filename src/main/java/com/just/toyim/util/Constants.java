package com.just.toyim.util;

import com.just.toyim.dao.meta.GroupInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Constants {

    public static final String USER_TOKEN = "userId";

    public static Map<String, WebSocketServerHandshaker> WSHandshakerMap =
            new ConcurrentHashMap<String, WebSocketServerHandshaker>();

    public static Map<String, ChannelHandlerContext> onlineUserMap =
            new ConcurrentHashMap<String, ChannelHandlerContext>();

}
