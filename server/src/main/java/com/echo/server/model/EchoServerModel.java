package com.echo.server.model;

/**
 * Server's Model include port,address
 */
public class EchoServerModel {
    private int port;
    private String addr;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "EchoServerModel{" +
                "port=" + port +
                ", addr='" + addr + '\'' +
                '}';
    }
}
