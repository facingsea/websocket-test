package com.facingsea.ws.bootstrap;

import org.apache.log4j.Logger;

import com.facingsea.ws.utils.Constant;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class WSServer {
	
	private static Logger log = Logger.getLogger(WSServer.class);

	public static void main(String[] args) {
		// 用于serversocketchannel的eventloop
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// 用于处理accept到的channel
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap boot = new ServerBootstrap();
			//设置时间循环对象，前者用来处理accept事件，后者用于处理已经建立的连接的io
			boot.group(bossGroup, workGroup);
			//用它来建立新accept的连接，用于构造serversocketchannel的工厂类
			boot.channel(NioServerSocketChannel.class);
			
			boot.childHandler(new WSServerInitializer());
			
			//bind方法会创建一个serverchannel，并且会将当前的channel注册到eventloop上面，  
            //会为其绑定本地端口，并对其进行初始化，为其的pipeline加一些默认的handler  
			ChannelFuture f = boot.bind(Constant.PORT);
			Channel channel = f.channel();
			log.debug("server start: " + (Constant.SSL? "wss" : "ws") + "://127.0.0.1:" + Constant.PORT + Constant.WEBSOCKET_PATH);
			//相当于在这里阻塞，直到serverchannel关闭 
			channel.closeFuture().sync();
		} catch (Exception e) {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}

}
