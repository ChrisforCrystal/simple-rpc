package com.github.liuzhengyang.simplerpc.core;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.Method;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-12-16
 */
public class RpcServerHandler extends SimpleChannelInboundHandler<Request> {
	private Object service;

	public RpcServerHandler(Object serviceImpl) {
		this.service = serviceImpl;
	}

	protected void channelRead0(ChannelHandlerContext ctx, Request msg) throws Exception {
		Class<?> clazz = msg.getClazz();
		String methodName = msg.getMethod();
		Object[] params = msg.getParams();
		Class<?>[] parameterTypes = new Class<?>[params.length];
		for (int i = 0; i < params.length; i++) {
			parameterTypes[i] = params[i].getClass();
		}
		long requestId = msg.getRequestId();
		Method method = service.getClass().getDeclaredMethod(methodName, parameterTypes);
		method.setAccessible(true);
		Object invoke = method.invoke(service, params);
		Response response = new Response();
		response.setRequestId(requestId);
		response.setResponse(invoke);
		ctx.pipeline().writeAndFlush(response);
	}
}
