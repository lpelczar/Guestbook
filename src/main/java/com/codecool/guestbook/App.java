package com.codecool.guestbook;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {
        // create a server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(7900), 0);

        // set routes
        server.createContext("/", new Guestbook());
        server.createContext("/css", new CssHandler());


        server.setExecutor(null); // creates a default executor

        // start listening
        server.start();
    }

}
