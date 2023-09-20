package ru.netology;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

// https://github.com/netology-code/jspr-homeworks/tree/master/01_web
// https://github.com/netology-code/jspr-homeworks/tree/master/02_forms
public class Main {
    public static void main(String[] args) {
        Server.addHandler("GET", "/classic.html", (request, out) -> {
                    final var filePath = Path.of(".", "public", request.getUri().getPath());
                    final var mimeType = Files.probeContentType(filePath);

                    final var template = Files.readString(filePath);
                    final var content = template.replace(
                            "{time}",
                            LocalDateTime.now().toString()
                    ).getBytes();
                    out.write((
                            "HTTP/1.1 200 OK\r\n" +
                                    "Content-Type: " + mimeType + "\r\n" +
                                    "Content-Length: " + content.length + "\r\n" +
                                    "Connection: close\r\n" +
                                    "\r\n"
                    ).getBytes());
                    out.write(content);
                    out.flush();
                }
        );

        Server.listen(9999);
    }
}


