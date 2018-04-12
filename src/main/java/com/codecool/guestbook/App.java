package com.codecool.guestbook;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class App {

    public static void main(String[] args) throws Exception {
        // create a server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8100), 0);

        // set routes
        server.createContext("/guestbook", new Guestbook());
        server.setExecutor(null); // creates a default executor

        // start listening
        server.start();
    }

}
