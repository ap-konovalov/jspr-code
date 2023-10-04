package ru.netology.service;

import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.model.PostEntity;
import ru.netology.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<PostEntity> all() {
        return repository.all();
    }

    public PostEntity getById(int id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public PostEntity save(PostEntity post) {
        return repository.save(post);
    }

    public void removeById(int id) {
        repository.removeById(id);
    }
}

