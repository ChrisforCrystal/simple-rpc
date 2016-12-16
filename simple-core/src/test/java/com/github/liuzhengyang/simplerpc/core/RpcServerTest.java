package com.github.liuzhengyang.simplerpc.core;

import com.github.liuzhengyang.simplerpc.core.example.HelloImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-12-16
 */
public class RpcServerTest {
	@Test
	public void init() throws Exception {
		RpcServer rpcServer = new RpcServer(8090, new HelloImpl());
		rpcServer.init();
	}

}