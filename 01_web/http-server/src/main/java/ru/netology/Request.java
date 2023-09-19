package ru.netology;

public class Request {

    private String method;

    private String path;

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public Request(String method, String path) {
        this.method = method;
        this.path = path;
    }
}
