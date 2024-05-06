package com.paf.socialmedia.repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.paf.socialmedia.document.Post_workout;

import java.util.List;

@Repository
public interface Post_workoutRepository<Post> extends MongoRepository<Post,String> {
    List<Post> findByUserId(String userId);

    void save(Post_workout post);
}
