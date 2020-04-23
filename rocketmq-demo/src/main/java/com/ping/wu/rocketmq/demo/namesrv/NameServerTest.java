package com.ping.wu.rocketmq.demo.namesrv;

import org.apache.rocketmq.common.namesrv.NamesrvConfig;
import org.apache.rocketmq.namesrv.NamesrvController;
import org.apache.rocketmq.remoting.netty.NettyServerConfig;

/**
 * @author wuping
 * @date 2020-04-22
 */

public class NameServerTest {
    public static void main(String[] args) throws Exception {
        // 业务配置
        final NamesrvConfig namesrvConfig = new NamesrvConfig();
        // 网络参数
        final NettyServerConfig nettyServerConfig = new NettyServerConfig();
        nettyServerConfig.setListenPort(9876);
        NamesrvController namesrvController = new NamesrvController(namesrvConfig, nettyServerConfig);
        namesrvController.initialize();
        // 绑定9876端口，并绑定一堆handler
        namesrvController.start();
        System.in.read();
    }
}
