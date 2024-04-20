package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Post;
import com.example.repository.PostRepository;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class PostController {
    
    @Autowired
	private PostRepository postRepository;


    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }


    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post){
        return postRepository.save(post);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id ){
        Post post = postRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Post not exist with id :"+ id));
        return ResponseEntity.ok(post);
    }

}
