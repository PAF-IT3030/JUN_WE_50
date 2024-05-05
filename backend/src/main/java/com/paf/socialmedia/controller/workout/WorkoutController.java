package com.paf.socialmedia.controller.workout;

import com.paf.socialmedia.document.workout.Workout;
import com.paf.socialmedia.service.workout.WorkoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @PostMapping
    public ResponseEntity<?> savePost(@RequestBody Workout workout){
        return workoutService.savePost(workout);
    }
    @GetMapping
    public ResponseEntity<?> getPosts(){
        return workoutService.getPosts();
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getPostsByUserId(@PathVariable String id){
        return workoutService.getPostsByUserId(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable String id){
        return workoutService.getPostById(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePostById(@PathVariable String id, @RequestBody Workout workout){
        return  workoutService.updatePostById(id,workout);
    }
    @PutMapping("/like/{id}")
    public ResponseEntity<?> likePostById(@PathVariable String id, @RequestBody Workout workout){
        return  workoutService.likePostById(id,workout);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePostById(@PathVariable String id){
        return workoutService.deletePostById(id);
    }
}

