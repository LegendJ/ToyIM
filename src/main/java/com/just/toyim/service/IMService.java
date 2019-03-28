package com.just.toyim.service;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;

public interface IMService {


    void register(JSONObject packet, ChannelHandlerContext ctx);

    void singleSend(JSONObject packet, ChannelHandlerContext ctx);

    void groupSend(JSONObject packet, ChannelHandlerContext ctx);

    void FileMsgSingleSend(JSONObject packet, ChannelHandlerContext ctx);

    void FileMsgGroupSend(JSONObject packet, ChannelHandlerContext ctx);

    void remove(ChannelHandlerContext ctx);

    void typeError(ChannelHandlerContext ctx);


}
