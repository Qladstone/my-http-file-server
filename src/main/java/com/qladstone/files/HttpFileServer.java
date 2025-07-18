package com.qladstone.files;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;

import java.io.File;
import java.net.InetSocketAddress;
import java.nio.file.Path;

public class HttpFileServer {
    public static void main(String[] args) {

        System.out.println("Running HTTP file server...");

        String portProperty = System.getProperty("app.port");
        int port = portProperty == null ? 8080 : Integer.parseInt(portProperty);

        String rootPathProperty = System.getProperty("app.root");
        String absRootPath = new File(rootPathProperty).getAbsolutePath();

        System.out.printf("Serving files on port %d from directory: %s\n", port, absRootPath);
        HttpServer fileServer = SimpleFileServer.createFileServer(
                new InetSocketAddress(port),
                Path.of(absRootPath),
                SimpleFileServer.OutputLevel.INFO
        );
        fileServer.start();
        System.out.printf("View files here: http://localhost:%d\n", port);
    }
}