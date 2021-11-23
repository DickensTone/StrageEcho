package com.echo.server;

import com.echo.core.start.EchoApplicationStart;
import com.echo.server.fileserver.FileServer;

import java.io.IOException;

/**
 * Start the server.
 * <p>
 *     Include sent file's sever and receive file's server.
 *     Base on the listener-address.properties
 * </p>
 */
public class App {
    public static void main(String[] args) {
        try {
//            EchoApplicationStart.run();
            new FileServer().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
