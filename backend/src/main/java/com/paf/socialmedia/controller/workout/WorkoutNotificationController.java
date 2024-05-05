package com.paf.socialmedia.controller.workout;

import com.paf.socialmedia.document.workout.WorkoutNotification;
import com.paf.socialmedia.service.workout.WorkoutNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/workout/notifications")
public class WorkoutNotificationController {
    @Autowired
    private WorkoutNotificationService workoutNotificationService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getNotificationById(@PathVariable String id){
        return workoutNotificationService.getNotificationById(id);
    }
    @GetMapping
    public ResponseEntity<?> getNotifications(){
        return workoutNotificationService.getNotifications();
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUnreadNotificationsByUserId(@PathVariable String id){
        return workoutNotificationService.getUnreadNotificationsByUserId(id);
    }
    @PostMapping
    public ResponseEntity<?> saveNotification(@RequestBody WorkoutNotification workoutNotification){
        return workoutNotificationService.saveNotification(workoutNotification);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateNotificationById(@PathVariable String id, @RequestBody WorkoutNotification workoutNotification){
        return  workoutNotificationService.updateNotificationById(id,workoutNotification);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotificationById(@PathVariable String id){
        return workoutNotificationService.deleteNotificationById(id);
    }
}

