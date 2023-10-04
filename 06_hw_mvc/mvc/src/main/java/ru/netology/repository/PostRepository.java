package ru.netology.repository;

import ru.netology.model.PostEntity;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    List<PostEntity> all();

    Optional<PostEntity> getById(int id);

    PostEntity save(PostEntity post);

    void removeById(int id);
}
