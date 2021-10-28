package com.echo.core.builder;

import com.echo.core.model.ServerPorts;

import java.util.LinkedList;
import java.util.List;

public class ServerWorker {

    private LinkedList<Integer> ports = new LinkedList<>();

    private ServerWorker(){

    }

    public static ServerWorker getInstance(){
        return EchoServer.serverWorker;
    }

    public void startServers(ServerPorts<Integer> serverPort) {
        List<Integer> listPort = serverPort.getPorts();
        System.out.println(listPort);
    }



    private static class EchoServer{
        private static final ServerWorker serverWorker = new ServerWorker();
    }
}
