package com.echo.server.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileUtils {

    public static byte[] getFile(String path){
        byte[] out = new byte[500];
        try (RandomAccessFile raf = new RandomAccessFile(path,"r")){
            System.out.println(raf.length());
            raf.read(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;

    }

}
