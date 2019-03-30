package com.just.toyim.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.just.toyim.dao.GroupDao;
import com.just.toyim.dao.UserDao;
import com.just.toyim.dao.meta.GroupInfo;
import com.just.toyim.netty.timeline.MemTimeline;
import com.just.toyim.netty.timeline.Timeline;
import com.just.toyim.service.IMService;
import com.just.toyim.service.meta.HttpResponse;
import com.just.toyim.service.meta.IMCodeEnum;
import com.just.toyim.util.Constants;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class IMServiceImpl implements IMService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IMServiceImpl.class);

    @Autowired
    GroupDao groupDao;

    @Autowired
    UserDao userDao;



    @Override
    public void register(JSONObject packet, ChannelHandlerContext ctx) {
        long userId = (long) packet.get("userId");
        Constants.onlineUserMap.put(userId, ctx);
        checkAndPush(ctx,userId);
        String responce = new HttpResponse().success()
                .setData("type", IMCodeEnum.REGISTER)
                .toString();
        sendMessage(ctx, responce);
        LOGGER.info(MessageFormat.format("userId为 {0} 的用户登记到在线用户表，当前在线人数为：{1}"
                , userId, Constants.onlineUserMap.size()));
    }

    @Override
    public void p2pSend(JSONObject packet, ChannelHandlerContext ctx) {
        long fromUserId = (long) packet.get("fromUserId");
        long toUserId = (long) packet.get("toUserId");
        String content = (String) packet.get("content");
        ChannelHandlerContext toUserCtx = Constants.onlineUserMap.get(toUserId);
        HttpResponse response = new HttpResponse().success()
                .setData("fromUserId", fromUserId)
                .setData("content", content)
                .setData("type", IMCodeEnum.SINGLE_SENDING);

        if (toUserCtx == null) {
            // 缓存离线消息
            cacheMessage(toUserId,response);
        } else {
            sendMessage(toUserCtx, response.toString());
        }
    }

    @Override
    public void groupSend(JSONObject packet, ChannelHandlerContext ctx) {
        long fromUserId = (long) packet.get("fromUserId");
        long toGroupId = (long) packet.get("toGroupId");
        String content = (String) packet.get("content");

        GroupInfo groupInfo = groupDao.get(toGroupId);
        if (groupInfo == null) {
            String responseJson = new HttpResponse().error("该群id不存在").toString();
            sendMessage(ctx, responseJson);
        } else {
            HttpResponse response = new HttpResponse().success()
                    .setData("fromUserId", fromUserId)
                    .setData("content", content)
                    .setData("toGroupId", toGroupId)
                    .setData("type", IMCodeEnum.GROUP_SENDING);

            userDao.findGroupUsers(toGroupId).forEach(member -> {
                        long toUserId = member.getId();
                        ChannelHandlerContext toCtx = Constants.onlineUserMap.get(toUserId);

                        if (toCtx == null) {
                            cacheMessage(toUserId,response);
                        }else if (toUserId != fromUserId) {
                            sendMessage(toCtx, response.toString());
                        }
                    });
        }
    }

    @Override
    public void FileMsgP2pSend(JSONObject packet, ChannelHandlerContext ctx) {

    }

    @Override
    public void FileMsgGroupSend(JSONObject packet, ChannelHandlerContext ctx) {

    }

    @Override
    public void remove(ChannelHandlerContext ctx) {
        Constants.onlineUserMap.forEach((k,onlineCtx)->{
            if(onlineCtx == ctx){
                LOGGER.info("正在移除握手实例...");
                Constants.WSHandshakerMap.remove(ctx.channel().id().asLongText());
                LOGGER.info(MessageFormat.format("已移除握手实例，当前握手实例总数为：{0}"
                        , Constants.WSHandshakerMap.size()));
                LOGGER.info(MessageFormat.format("userId为 {0} 的用户已退出聊天，当前在线人数为：{1}"
                        , k, Constants.onlineUserMap.size()));
            }
        });
    }

    @Override
    public void typeError(ChannelHandlerContext ctx) {
        String response = new HttpResponse()
                .error("该类型不存在！")
                .toString();
        sendMessage(ctx, response);
    }

    private void checkAndPush(ChannelHandlerContext ctx,Long userId){
        Timeline<HttpResponse> t = Constants.msgSyncer.get(userId);
        if(t == null){
            Timeline<HttpResponse> timeline = new MemTimeline<>();
            Constants.msgSyncer.put(userId, timeline);
        }else if (!t.isEmpty()){
            // push all msg
            t.getAll().forEach(msg->sendMessage(ctx,msg.toString()));
        }
    }

    private void cacheMessage(long toUserId,HttpResponse msg){
        if(Constants.msgSyncer.get(toUserId) == null){
            Timeline<HttpResponse> timeline = new MemTimeline<>();
            Constants.msgSyncer.put(toUserId, timeline);
        }
        Constants.msgSyncer.get(toUserId).add(msg);
    }

    private void sendMessage(ChannelHandlerContext ctx, String message) {
        ctx.channel().writeAndFlush(new TextWebSocketFrame(message));
    }
}
