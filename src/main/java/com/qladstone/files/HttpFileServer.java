package com.qladstone.files;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;

import java.io.File;
import java.net.InetSocketAddress;
import java.nio.file.Path;

public class HttpFileServer {
    public static void main(String[] args) {
        printUsage();

        System.out.println("Running HTTP file server...");

        String portProperty = System.getProperty("app.port");
        int port = portProperty == null ? 8080 : Integer.parseInt(portProperty);

        String rootPathProperty = System.getProperty("app.root");
        if (rootPathProperty == null) throw new RuntimeException("Missing app.root property!");
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

    private static void printUsage() {
        System.out.println("""
                Usage:
                java -Dapp.root=<root> -Dapp.port=<port> -jar my-http-file-server-*.jar
                \troot: Absolute or relative path to file root directory.
                \tport: optional port number to serve, default is 8080.
                """);
    }
}