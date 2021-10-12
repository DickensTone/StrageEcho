package com.echo.server.config;

public enum NacosServerConfig {
    localhost("loacalhost", 8848, 5000);

    NacosServerConfig(String addr, int port, int timeout) {
        this.addr = addr;
        this.port = port;
        this.timeout = timeout;
    }
    private String addr;
    private int port;
    private int timeout;
    
    public String addr(){
        return addr;
    }
    public int port(){
        return port;
    }

    public int timeout(){
        return timeout;
    }


}
