package ru.netology;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Request {

    private final String method;

    private final URI uri;

    public URI getUri() {
        return uri;
    }

    public String getMethod() {
        return method;
    }

    public Request(String method, URI uri) {
        this.method = method;
        this.uri = uri;
    }

    public List<NameValuePair> getQueryParams() {
        return URLEncodedUtils.parse(uri, StandardCharsets.UTF_8);
    }
}
