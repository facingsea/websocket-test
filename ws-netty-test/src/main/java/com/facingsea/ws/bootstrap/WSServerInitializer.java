package com.facingsea.ws.bootstrap;

import com.facingsea.ws.utils.Constant;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

public class WSServerInitializer extends ChannelInitializer<SocketChannel> {

	// 当新连接accept的时候，这个方法会调用
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline line = ch.pipeline();
		line.addLast("decoder", new HttpRequestDecoder()); // 用于解析http报文的handler
		line.addLast("aggregator", new HttpObjectAggregator(65526)); // 用于将解析出来的数据封装成http对象，httprequest等
		line.addLast("encoder", new HttpResponseEncoder()); // 用于将response编码成httpresponse报文发送
		line.addLast("handshake", new WebSocketServerProtocolHandler(Constant.WEBSOCKET_PATH, "", true)); // websocket的handler部分定义的，它会自己处理握手等操作
		line.addLast(new WSServerHandler());
	}

}
