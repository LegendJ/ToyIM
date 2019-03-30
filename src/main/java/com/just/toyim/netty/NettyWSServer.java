package com.just.toyim.netty;

import com.just.toyim.netty.handler.HttpHandler;
import com.just.toyim.netty.handler.WSHandler;
import com.just.toyim.netty.handler.WSIdleHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NettyWSServer {

    private static final Logger logger = LoggerFactory.getLogger(NettyWSServer.class);

    private EventLoopGroup bossGroup = new NioEventLoopGroup();

    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    private ServerBootstrap serverBootstrap = new ServerBootstrap();

    private ChannelFuture serverChannelFuture;

    @Autowired
    private HttpHandler httpHandler;

    @Autowired
    private WSHandler wsHandler;

    @Autowired
    private WSIdleHandler wsIdleHandler;


    public void start(int port) {
        try {
            long begin = System.currentTimeMillis();
            serverBootstrap.group(bossGroup, workerGroup) //boss辅助客户端的tcp连接请求  worker负责与客户端之前的读写操作
                    .channel(NioServerSocketChannel.class) //配置客户端的channel类型
                    .option(ChannelOption.SO_BACKLOG, 1024) //配置TCP参数，握手字符串长度设置
                    .option(ChannelOption.TCP_NODELAY, true) //TCP_NODELAY算法，尽可能发送大块数据，减少充斥的小块数据
                    .childOption(ChannelOption.SO_KEEPALIVE, true)//开启心跳包活机制，就是客户端、服务端建立连接处于ESTABLISHED状态，超过2小时没有交流，机制会被启动
                    .childOption(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(592048))//配置固定长度接收缓存区分配器
                    .childHandler(new ChannelInitializer<NioSocketChannel>(){
                        @Override
                        protected void initChannel(NioSocketChannel ch) {
                            ch.pipeline().addLast("idle-discover", wsIdleHandler); // 空闲检测
                            ch.pipeline().addLast("http-codec", new HttpServerCodec()); // HTTP编码解码器
                            ch.pipeline().addLast("aggregator", new HttpObjectAggregator(65536)); // 把HTTP头、HTTP体拼成完整的HTTP请求
                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler()); // 方便大文件传输，不过实质上都是短的文本数据
                            ch.pipeline().addLast("http-handler", httpHandler);
                            ch.pipeline().addLast("http-handler", wsHandler);
                        }
                    }); //绑定I/O事件的处理类
            long end = System.currentTimeMillis();
            logger.info("Netty Websocket start successfully, takes " + (end - begin) + " ms, listening on port: " + port);

            serverChannelFuture = serverBootstrap.bind(port).sync();
        } catch (Exception e) {
            logger.info(e.getMessage());
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            e.printStackTrace();
        }

    }

    /**
     * bossGroupFuture = bossGroup.shutdownGracefully();
     * workerGroupFuture = workerGroup.shutdownGracefully();
     * cause memory leak issue without releasing server connection.
     */
    public void close() {
        serverChannelFuture.channel().close();// release server-side connection
        Future<?> bossGroupFuture = bossGroup.shutdownGracefully();
        Future<?> workerGroupFuture = workerGroup.shutdownGracefully();

        try {
            bossGroupFuture.await();
            workerGroupFuture.await();
        } catch (InterruptedException ok) {
            ok.printStackTrace();
        }
    }


}
