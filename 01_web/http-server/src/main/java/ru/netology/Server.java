package ru.netology;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {

    static final List<String> validPaths = List.of("/index.html", "/spring.svg", "/spring.png", "/resources.html", "/styles.css", "/app.js", "/links.html", "/forms.html", "/classic.html", "/events.html", "/events.js");

    static final Executor executor = Executors.newFixedThreadPool(64);

    static ConcurrentMap<ConcurrentMap<String, String>, Handler> requestHandlers = new ConcurrentHashMap<>();

    private Server() {
    }

    public static void listen(int port) {
        try (final var serverSocket = new ServerSocket(port)) {
            while (!serverSocket.isClosed()) {
                final var socket = serverSocket.accept();
                final var connectionHandler = new ConnectionHandler(socket);
                executor.execute(connectionHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addHandler(String method, String path, Handler handler) {
        ConcurrentMap<String, String> key = new ConcurrentHashMap<>();
        key.put(method, path);
        requestHandlers.put(key, handler);
    }
}
