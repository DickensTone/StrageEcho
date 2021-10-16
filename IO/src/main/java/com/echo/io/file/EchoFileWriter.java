package com.echo.io.file;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class EchoFileWriter implements Runnable{
    private byte[] fileByte;
    String path;

    public void writeToDesk(){
        try {
            FileOutputStream out = new FileOutputStream("C:\\Users\\ddt\\Desktop\\out.txt");
            out.write(fileByte);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFileByte(byte[] fileByte) {
        this.fileByte = fileByte;
    }

    public void setPath(String path) {
        this.path = path;
    }


    @Override
    public void run() {
        try (FileOutputStream out = new FileOutputStream(path);){
            out.write(fileByte);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
