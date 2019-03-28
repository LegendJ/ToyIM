package com.just.toyim.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.just.toyim.service.IMService;
import io.netty.channel.ChannelHandlerContext;

public class IMServiceImpl implements IMService {

    @Override
    public void register(JSONObject packet, ChannelHandlerContext ctx) {

    }

    @Override
    public void singleSend(JSONObject packet, ChannelHandlerContext ctx) {

    }

    @Override
    public void groupSend(JSONObject packet, ChannelHandlerContext ctx) {

    }

    @Override
    public void FileMsgSingleSend(JSONObject packet, ChannelHandlerContext ctx) {

    }

    @Override
    public void FileMsgGroupSend(JSONObject packet, ChannelHandlerContext ctx) {

    }

    @Override
    public void remove(ChannelHandlerContext ctx) {

    }

    @Override
    public void typeError(ChannelHandlerContext ctx) {

    }
}
