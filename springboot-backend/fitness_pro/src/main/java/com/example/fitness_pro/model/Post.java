package com.example.fitness_pro.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_description")
    private String postDescription;

    @Column(name = "post_image_1")
    private String imageFileName1;

    @Column(name = "post_image_2")
    private String imageFileName2;

    @Column(name = "post_image_3")
    private String imageFileName3;

    @Column(name = "post_date")
    private Date createdAt;

    @Column(name = "username")
    private String username; // New field for username

    public Post() {
        this.createdAt = new Date(); // Default to current date when creating a new post
    }

    public Post(String postDescription, String imageFileName1, String imageFileName2, String imageFileName3, String username) {
        this.postDescription = postDescription;
        this.imageFileName1 = imageFileName1;
        this.imageFileName2 = imageFileName2;
        this.imageFileName3 = imageFileName3;
        this.username = username;
        this.createdAt = new Date(); // Default to current date when creating a new post
    }

    // Getters and setters for all fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getImageFileName1() {
        return imageFileName1;
    }

    public void setImageFileName1(String imageFileName1) {
        this.imageFileName1 = imageFileName1;
    }

    public String getImageFileName2() {
        return imageFileName2;
    }

    public void setImageFileName2(String imageFileName2) {
        this.imageFileName2 = imageFileName2;
    }

    public String getImageFileName3() {
        return imageFileName3;
    }

    public void setImageFileName3(String imageFileName3) {
        this.imageFileName3 = imageFileName3;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
