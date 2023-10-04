package ru.netology.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.netology.model.PostDto;
import ru.netology.model.PostEntity;
import ru.netology.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService service;

    @Autowired
    private ModelMapper modelMapper;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<PostDto> all() {
        return service.all().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PostDto getById(@PathVariable int id) {
        return convertToDto(service.getById(id));
    }

    @PostMapping
    public PostDto save(@RequestBody PostDto post) {
        PostEntity postEntity = convertToEntity(post);
        PostEntity postEntityCreated = service.save(postEntity);
        return convertToDto(postEntityCreated);
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable int id) {
        service.removeById(id);
    }

    private PostDto convertToDto(PostEntity postEntity) {
        return modelMapper.map(postEntity, PostDto.class);
    }

    private PostEntity convertToEntity(PostDto post) {
        return modelMapper.map(post, PostEntity.class);
    }
}
