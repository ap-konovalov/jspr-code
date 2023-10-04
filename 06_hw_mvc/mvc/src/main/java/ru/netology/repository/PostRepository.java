package ru.netology.repository;

import ru.netology.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    List<Post> all();

    Optional<Post> getById(int id);

    Post save(Post post);

    void removeById(int id);
}
