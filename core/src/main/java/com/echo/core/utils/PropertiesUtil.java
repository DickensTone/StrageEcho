package com.echo.core.utils;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.*;
import java.util.Properties;


public class PropertiesUtil {

    /**
     * read the properties through InputStream
     * <P>
     *     a file in resources/file.xml
     *     the path is file.xml;
     * </P>
     *
     *
     * @param path a class path
     * @return Properties
     */
    public static Properties getProperties(String path) throws IOException {
        InputStream is;
        is = ClassLoader.getSystemResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException(" cannot be opened because it does not exist");
        }

        Properties properties = new Properties();
        properties.load(is);

        return properties;
    }
}
