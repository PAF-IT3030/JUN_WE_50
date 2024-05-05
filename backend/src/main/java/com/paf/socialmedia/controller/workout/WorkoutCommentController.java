package com.paf.socialmedia.controller.workout;

import com.paf.socialmedia.document.workout.WorkoutComment;
import com.paf.socialmedia.service.workout.WorkoutCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/workout/comments")
public class WorkoutCommentController {
    @Autowired
    private WorkoutCommentService workoutCommentService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable String id){
        return workoutCommentService.getCommentById(id);
    }
    @GetMapping
    public ResponseEntity<?> getComments(){
        return workoutCommentService.getComments();
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> getCommentsByPost(@PathVariable String id){
        return workoutCommentService.getCommentsByPost(id);
    }
    @PostMapping
    public ResponseEntity<?> saveComment(@RequestBody WorkoutComment workoutComment){
        return workoutCommentService.saveComment(workoutComment);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCommentById(@PathVariable String id, @RequestBody WorkoutComment workoutComment){
        return  workoutCommentService.updateCommentById(id,workoutComment);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCommentById(@PathVariable String id){
        return workoutCommentService.deleteCommentById(id);
    }
}

