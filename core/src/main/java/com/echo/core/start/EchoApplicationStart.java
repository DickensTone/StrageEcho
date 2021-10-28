package com.echo.core.start;


import com.echo.core.builder.ServerWorker;
import com.echo.core.model.ServerPorts;
import com.echo.core.utils.ListUtils;
import com.echo.core.utils.PropertiesUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * A class that start the MainApplication of Echo.
 * This class will imitate the class of SpringBootApplication.
 */
public class EchoApplicationStart {
    public static void run() throws Exception {

        //load the ports of server

        Properties properties = PropertiesUtil.getProperties("listener-address.properties");
        if(!properties.containsKey("serverPort")){
            throw new Exception("serverPort not found");
        }

        String ports = properties.getProperty("serverPort");
        String[] portsArray = ports.split(",");
        ServerPorts<Integer> serverPorts = new ServerPorts<>(ListUtils.getArrayListFromStringArray(portsArray));

        //start the server
        ServerWorker serverWorker = ServerWorker.getInstance();

        serverWorker.startServers(serverPorts);

    }
}
