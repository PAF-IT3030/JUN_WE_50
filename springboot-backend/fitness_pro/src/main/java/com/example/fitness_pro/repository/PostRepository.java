package com.example.fitness_pro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fitness_pro.model.Post;



public interface PostRepository extends JpaRepository<Post, Long>{

}
