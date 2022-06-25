package com.echo.client.channel;

import com.echo.client.config.databaseConfig.DataBaseContextHolder;
import com.echo.client.domain.Transport;
import com.echo.client.domain.enums.DataBaseType;
import com.echo.client.netty.handler.EchoClientHandler;
import com.echo.client.repository.ServiceLog;
import com.echo.client.service.transportLog.WriteQueue;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;


@SpringBootTest
public class ClientTest {

    @Autowired
    private WriteQueue writeQueue;


    @Autowired
    private ServiceLog serviceLog;

    @Test
    public void test() throws InterruptedException {
        ByteBuf buf = Unpooled.copiedBuffer("This 23:30", StandardCharsets.UTF_8);

        EmbeddedChannel embeddedChannel = new EmbeddedChannel( EchoClientHandler.getBuilder(writeQueue).build());

        embeddedChannel.writeInbound(buf.retain());
        DataBaseContextHolder.set(DataBaseType.Embedded);
        Thread.sleep(1000);
        Iterable<Transport>  iterable = serviceLog.findAll();
        for (Transport transport : iterable) {
            System.out.println(transport);
        }

        embeddedChannel.readInbound();
        ByteBuf read = embeddedChannel.readOutbound();

        buf.release();
        read.release();
    }


}
