package com.echo.io.file;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class FileWorker {
    private byte[] fileByte;
    String path;
    private ScheduledThreadPoolExecutor executor;

    public void setFileByte(byte[] fileByte) {
        this.fileByte = fileByte;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void write(Runnable r){
        this.executor.execute(r);
    }

    public FileWorker(){
        this.executor = new ScheduledThreadPoolExecutor(1, r -> {
            Thread t = new Thread(r);
            t.setName("FileWorkers");
            t.setDaemon(true);
            return t;
        });
    }
}
