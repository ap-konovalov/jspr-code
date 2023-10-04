package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.PostEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryStubImpl implements PostRepository {
    private static final AtomicInteger currentId = new AtomicInteger(0);

    private final List<PostEntity> posts = Collections.synchronizedList(new ArrayList<>());

    public List<PostEntity> all() {
        return posts.stream()
                .filter(postEntity -> !postEntity.getIsRemoved())
                .collect(Collectors.toList());
    }

    public Optional<PostEntity> getById(int id) {
        return posts.stream().filter(post -> post.getId() == id)
                .filter(post -> !post.getIsRemoved())
                .findFirst();
    }

    public PostEntity save(PostEntity post) {
        if (post.getId() == 0) {
            PostEntity newPost = new PostEntity(currentId.incrementAndGet(), post.getContent(), false);
            posts.add(newPost);
            return newPost;
        }
        Optional<PostEntity> postToBeUpdated = getById(post.getId());
        if (!postToBeUpdated.isPresent()) {
            throw new NotFoundException("Can't update post. Post with id '" + post.getId() + "' not found in store.");
        }
        PostEntity updatedPost = postToBeUpdated.get();
        posts.remove(updatedPost);
        updatedPost.setContent(post.getContent());
        posts.add(updatedPost);
        return updatedPost;
    }

    public void removeById(int id) {
        Optional<PostEntity> postToBeRemoved = getById(id);
        if (!postToBeRemoved.isPresent()) {
            throw new NotFoundException("Can't remove post. Post with id '" + id + "' not found in store.");
        }
        PostEntity postEntityToBeRemoved = postToBeRemoved.get();
        postEntityToBeRemoved.setIsRemoved(true);
    }
}