package org.example.yolofarmbe.Controller;

import org.example.yolofarmbe.Entity.ActivityLog;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Response.ActivityLogResponse;
import org.example.yolofarmbe.Service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activitylogs")
public class ActivityLogController {
    @Autowired
    private ActivityLogService activityLogService;

    @GetMapping
    public List<ActivityLog> getAllActivityLog(){
        return activityLogService.getAllActivityLogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityLogResponse> getAActivityLog(@PathVariable int id){
        try {
            ActivityLogResponse activityLogResponse = activityLogService.getAActivityLog(id);
            return ResponseEntity.ok(activityLogResponse);
        }catch (ResourceNotFoundException e){
            return  ResponseEntity.ok(ActivityLogResponse.builder()
                    .message("Activity Log with id "+id+" doesn't exits!")
                    .activityLog(null)
                    .build());
        }
    }

    @PostMapping
    public ResponseEntity<ActivityLogResponse> addANewActivityLog(@RequestBody ActivityLog activityLog){
        ActivityLogResponse activityLogResponse = activityLogService.addAActivityLog(activityLog);
        return ResponseEntity.ok(activityLogResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityLogResponse> updateAAcitivityLog(@RequestBody ActivityLog activityLog, @PathVariable int id){
        try {
            ActivityLogResponse activityLogResponse = activityLogService.updateAActivityLog(id, activityLog);
            return ResponseEntity.ok(activityLogResponse);
        }catch (ResourceNotFoundException e){
            return  ResponseEntity.ok(ActivityLogResponse.builder()
                    .message("Activity Log or farm doesn't exits!")
                    .activityLog(null)
                    .build());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ActivityLogResponse> deleteAActivityLog(@PathVariable int id){
        try {
            ActivityLogResponse activityLogResponse = activityLogService.deleteAActivityLog(id);
            return ResponseEntity.ok(activityLogResponse);
        }catch (ResourceNotFoundException e){
            return  ResponseEntity.ok(ActivityLogResponse.builder()
                    .message("Activity Log with id "+id+" doesn't exits!")
                    .activityLog(null)
                    .build());
        }
    }
}
