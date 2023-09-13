package com.jyusun.origin.base.launch.server.config;

import com.jyusun.origin.base.launch.server.core.listener.ServerListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 服务器信息
 *
 * @author jyusun at 2019-08-08
 */
@Configuration(proxyBeanMethods = false)
@Import(ServerListener.class)
public class ServerConfiguration {

}
