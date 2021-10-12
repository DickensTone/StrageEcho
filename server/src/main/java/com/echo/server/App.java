package com.echo.server;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.echo.server.config.NacosServerConfig;
import com.echo.server.echoServer.EchoServer;
import com.echo.server.utils.MapUtils;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class App {

    public static void main(String[] args){

        //发布配置
        pushProperties();
        //获取配置并启动服务
        initServer();

    }

    private static void initServer() {
        try {
            String serverAddr = NacosServerConfig.localhost.addr();
            String dataId = "com-echo-EchoServer";
            String group = "localhost";
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(dataId, group, NacosServerConfig.localhost.timeout());
            System.out.println(content);

            Map<String, String> serverConfig = MapUtils.parseJson(content);
            if(!serverConfig.containsKey("port")) {
                throw new Exception("Not get successful info from Nacos：Lost port");
            }

            System.out.println(serverConfig.toString());
            Integer port = Integer.parseInt(serverConfig.get("port"));
            new EchoServer(5555).start();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    private static void pushProperties(){

        try {
            // 初始化配置服务，控制台通过示例代码自动获取下面参数
            String serverAddr = NacosServerConfig.localhost.addr();
            String dataId = "com-echo-EchoServer";
            String group = "localhost";
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);

            Map<String, String> server = new HashMap<>();
            server.put("port","5555");
            boolean isPublishOk = configService.publishConfig(dataId, group, MapUtils.toJson(server), "json");
            System.out.println(isPublishOk);
        } catch (NacosException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
