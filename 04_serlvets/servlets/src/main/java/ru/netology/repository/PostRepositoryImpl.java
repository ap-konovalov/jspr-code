package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PostRepositoryImpl implements PostRepository {

    private static final AtomicInteger currentId = new AtomicInteger(0);

    private final List<Post> posts = Collections.synchronizedList(new ArrayList<>());

    public List<Post> all() {
        return posts;
    }

    public Optional<Post> getById(int id) {
        return posts.stream().filter(post -> post.getId() == id)
                .findFirst();
    }

    public Post save(Post post) throws IllegalArgumentException {
        if (post.getId() == 0) {
            Post newPost = new Post(currentId.incrementAndGet(), post.getContent());
            posts.add(newPost);
            return newPost;
        }
        Optional<Post> postToBeUpdated = getById(post.getId());
        if (!postToBeUpdated.isPresent()) {
            throw new IllegalArgumentException("Can't update post. Post with id '" + post.getId() + "' not found in store.");
        }
        Post updatedPost = postToBeUpdated.get();
        posts.remove(updatedPost);
        updatedPost.setContent(post.getContent());
        posts.add(updatedPost);
        return updatedPost;
    }

    public void removeById(int id) throws IllegalArgumentException {
        Optional<Post> postToBeRemoved = getById(id);
        if (!postToBeRemoved.isPresent()) {
            throw new IllegalArgumentException("Can't remove post. Post with id '" + id + "' not found in store.");
        }
        posts.remove(postToBeRemoved.get());
    }
}
