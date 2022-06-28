package com.echo.server.init;

import com.echo.server.EchoServerProperties;
import com.echo.server.fileserver.FileServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ServerStart implements ApplicationRunner {

    final
    EchoServerProperties serverProperties;

    public ServerStart(EchoServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileServer fileServer = new FileServer(serverProperties.getPort());
        fileServer.start();
    }

}
