package ru.netology.servlet;

import ru.netology.controller.PostController;
import ru.netology.repository.PostRepository;
import ru.netology.service.PostService;
import ru.netology.utils.DigitUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static ru.netology.emums.HttpMethods.*;

public class MainServlet extends HttpServlet {
    public static final String POSTS_PATH = "/api/posts";
    public static final String POST_WITH_DIGIT = POSTS_PATH + "/\\d+";
    private PostController controller;

    @Override
    public void init() {
        final var repository = new PostRepository();
        final var service = new PostService(repository);
        controller = new PostController(service);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            final var path = req.getRequestURI();
            final var method = req.getMethod();
            if (method.equals(GET.name()) && path.equals(POSTS_PATH)) {
                controller.all(resp);
                return;
            }
            if (method.equals(GET.name()) && path.matches(POST_WITH_DIGIT)) {
                final var id = DigitUtils.getDigit(path);
                controller.getById(id, resp);
                return;
            }
            if (method.equals(POST.name()) && path.equals(POSTS_PATH)) {
                controller.save(req.getReader(), resp);
                return;
            }
            if (method.equals(DELETE.name()) && path.matches(POST_WITH_DIGIT)) {
                final var id = DigitUtils.getDigit(path);
                controller.removeById(id, resp);
                return;
            }
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

