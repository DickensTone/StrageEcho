package com.echo.server.linster;

import com.echo.server.fileserver.FileServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

@Service
public class ServerStart implements ApplicationListener<ContextRefreshedEvent>{

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, FileServer> beansOfType = event.getApplicationContext().getBeansOfType(FileServer.class);
        Collection<FileServer> values = beansOfType.values();
        for (FileServer requestHandler : values) {

            Class<?> clazz = requestHandler.getClass();

            try {
                Method method = clazz.getMethod("start");
                method.invoke(requestHandler);
            } catch (Exception e) {
                //ignore.
            }
        }
    }
}
