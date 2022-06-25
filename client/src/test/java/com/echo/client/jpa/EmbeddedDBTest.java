package com.echo.client.jpa;


import com.echo.client.config.databaseConfig.DataBaseContextHolder;
import com.echo.client.domain.Transport;
import com.echo.client.domain.enums.DataBaseType;
import com.echo.client.repository.ServiceLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

@SpringBootTest
public class EmbeddedDBTest {
    @Autowired
    private ServiceLog serviceLog;


    @Test
    public void test1(){
        DataBaseContextHolder.set(DataBaseType.Embedded);

        Transport transport = new Transport();
        transport.setModifyTime(Instant.now());
        transport.setCreateTime(Instant.now());
        transport = serviceLog.save(transport);

        serviceLog.save(transport);

        serviceLog.existsById(transport.getId());

        serviceLog.delete(transport);
    }


}
