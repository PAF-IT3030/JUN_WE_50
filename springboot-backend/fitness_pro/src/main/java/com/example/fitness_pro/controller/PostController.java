package com.example.fitness_pro.controller;

import com.example.fitness_pro.exception.ResourceNotFoundException;
import com.example.fitness_pro.model.Post;
import com.example.fitness_pro.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    // Get all posts
    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Create a new post
    @PostMapping("/posts")
    public Post createPost(@RequestParam("files") MultipartFile[] files,
                           @RequestParam("postDescription") String postDescription,
                           @RequestParam("username") String username) {
        // Validate input
        if (files == null || files.length != 3 || postDescription == null || postDescription.isEmpty() || username == null || username.isEmpty()) {
            // Handle invalid input
            // Return appropriate response or throw exception
        }

        // Handle directory creation
        String uploadDir = "public/images/";
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Process each file
        String[] imageFileNames = new String[3];
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            // Save the file
            String filePath = uploadDir + fileName;
            try {
                Path path = Paths.get(filePath);
                Files.write(path, file.getBytes());
                imageFileNames[i] = fileName;
            } catch (IOException ex) {
                // Handle file upload exception
                // Return appropriate response or throw exception
            }
        }

        // Create Post object
        Date createdAt = new Date();
        Post post = new Post(postDescription, imageFileNames[0], imageFileNames[1], imageFileNames[2], username);
        
        // Save Post object to database
        return postRepository.save(post);
    }

    // Get a post by ID
    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not exist with id :" + id));
        return ResponseEntity.ok(post);
    }

    // Update a post
    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id,
                                            @RequestParam("files") MultipartFile[] files,
                                            @RequestParam("postDescription") String postDescription,
                                            @RequestParam("username") String username) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not exist with id :" + id));

        // Validate input
        if (files == null || files.length != 3 || postDescription == null || postDescription.isEmpty() || username == null || username.isEmpty()) {
            // Handle invalid input
            // Return appropriate response or throw exception
        }

        // Handle directory creation
        String uploadDir = "public/images/";
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Process each file
        String[] imageFileNames = new String[3];
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            // Save the file
            String filePath = uploadDir + fileName;
            try {
                Path path = Paths.get(filePath);
                Files.write(path, file.getBytes());
                imageFileNames[i] = fileName;
            } catch (IOException ex) {
                // Handle file upload exception
                // Return appropriate response or throw exception
            }
        }

        // Update post details
        post.setPostDescription(postDescription);
        post.setImageFileName1(imageFileNames[0]);
        post.setImageFileName2(imageFileNames[1]);
        post.setImageFileName3(imageFileNames[2]);
        post.setUsername(username);
        post.setCreatedAt(new Date());

        // Save updated post
        Post updatedPost = postRepository.save(post);
        return ResponseEntity.ok(updatedPost);
    }
}
