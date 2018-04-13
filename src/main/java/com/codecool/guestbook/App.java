package com.codecool.guestbook;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class App {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(7900), 0);
        server.createContext("/guestbook", new GuestbookHandler());
        server.createContext("/static", new StaticHandler());
        server.setExecutor(null);
        server.start();
    }
}
