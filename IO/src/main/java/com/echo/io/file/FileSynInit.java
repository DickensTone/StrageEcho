package com.echo.io.file;

import com.echo.io.env.SystemProperty;

import java.io.File;

public class FileSynInit {

    public static void init(){
        createDirs();
    }

    public static boolean createDirs(){
        String path = System.getProperty("user.home") + SystemProperty.defaultSyncPath;

        File file = new File(path);

        if(file.exists()){
           return true;
        }

        return file.mkdirs();
    }


}
