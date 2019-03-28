package com.just.toyim.netty.handler;

import com.alibaba.fastjson.JSONObject;
import com.just.toyim.pojo.ResponseJson;
import com.just.toyim.service.IMService;
import com.just.toyim.service.impl.IMServiceImpl;
import com.just.toyim.util.Constants;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WSHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WSHandler.class);

    private IMService chatService = new IMServiceImpl();



    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
        handlerWebSocketFrame(ctx, msg);
    }


    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
        // 关闭请求
        if (frame instanceof CloseWebSocketFrame) {
            WebSocketServerHandshaker handshaker =
                    Constants.WSHandshakerMap.get(ctx.channel().id().asLongText());
            if (handshaker == null) {
                sendErrorMessage(ctx, "不存在的客户端连接！");
            } else {
                handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            }
            return;
        }
        // ping请求
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 只支持文本格式，不支持二进制消息
        if (!(frame instanceof TextWebSocketFrame)) {
            sendErrorMessage(ctx, "仅支持文本(Text)格式，不支持二进制消息");
        }

        // 客服端发送过来的消息
        String request = ((TextWebSocketFrame) frame).text();
        LOGGER.info("服务端收到新信息：" + request);
        JSONObject packet = null;
        try {
            packet = JSONObject.parseObject(request);
        } catch (Exception e) {
            sendErrorMessage(ctx, "JSON字符串转换出错！");
            e.printStackTrace();
        }
        if (packet == null) {
            sendErrorMessage(ctx, "参数为空！");
            return;
        }

        String type = (String) packet.get("type");
        switch (type) {
            case "REGISTER":
                chatService.register(packet, ctx);
                break;
            case "SINGLE_SENDING":
                chatService.singleSend(packet, ctx);
                break;
            case "GROUP_SENDING":
                chatService.groupSend(packet, ctx);
                break;
            case "FILE_MSG_SINGLE_SENDING":
                chatService.FileMsgSingleSend(packet, ctx);
                break;
            case "FILE_MSG_GROUP_SENDING":
                chatService.FileMsgGroupSend(packet, ctx);
                break;
            default:
                chatService.typeError(ctx);
                break;
        }
    }

    /**
     * 描述：客户端断开连接
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        chatService.remove(ctx);
    }

    /**
     * 异常处理：关闭channel
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    private void sendErrorMessage(ChannelHandlerContext ctx, String errorMsg) {
        String responseJson = new ResponseJson()
                .error(errorMsg)
                .toString();
        ctx.channel().writeAndFlush(new TextWebSocketFrame(responseJson));
    }

}
