package com.facingsea.ws.bootstrap;

import org.apache.log4j.Logger;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class WSServerHandler extends SimpleChannelInboundHandler<Object> {
	
	private Logger log = Logger.getLogger(WSServerHandler.class);

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
		log.debug("Get the message: " + msg.toString());
		if(msg instanceof TextWebSocketFrame){
			TextWebSocketFrame text = (TextWebSocketFrame) msg;
			log.debug("The msg is : " + text.text());
			ctx.channel().writeAndFlush(new TextWebSocketFrame("finish reading.."));
		}
	}
	
}
