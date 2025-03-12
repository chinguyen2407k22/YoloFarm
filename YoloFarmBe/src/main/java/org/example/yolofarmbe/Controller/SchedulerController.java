package org.example.yolofarmbe.Controller;

import org.example.yolofarmbe.Entity.Scheduler;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Request.SchedulerRequest;
import org.example.yolofarmbe.Response.SchedulerResponse;
import org.example.yolofarmbe.Service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedulers")
public class SchedulerController {
    @Autowired
    private SchedulerService schedulerService;

    @GetMapping("/{schedulerType}")
    public List<? extends Scheduler> getSchedulers(@PathVariable String schedulerType) {
        return schedulerService.getAllScheduler(schedulerType);
    }

    @GetMapping("/{schedulerType}/{id}")
    public ResponseEntity<SchedulerResponse> getScheduler(@PathVariable String schedulerType, @PathVariable int id) {
        try {
            return ResponseEntity.ok(schedulerService.getSchedulerById(schedulerType,id));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(
                    SchedulerResponse.builder()
                            .message(e.getMessage())
                            .type(schedulerType)
                            .scheduler(null)
                            .build()
            );
        }
    }

    @PostMapping("/{schedulerType}")
    public ResponseEntity<SchedulerResponse> addANewScheduler(@PathVariable String schedulerType, @RequestBody SchedulerRequest schedulerRequest){
        try {
            return ResponseEntity.ok(schedulerService.addScheduler(schedulerType,schedulerRequest));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(
                    SchedulerResponse.builder()
                            .message(e.getMessage())
                            .type(schedulerType)
                            .scheduler(null)
                            .build()
            );
        }
    }

    @PutMapping("/{schedulerType}/{id}")
    public ResponseEntity<SchedulerResponse> updateScheduler(@PathVariable String schedulerType, @PathVariable int id,@RequestBody SchedulerRequest schedulerRequest){
        try{
            return ResponseEntity.ok(schedulerService.updateScheduler(schedulerType,id,schedulerRequest));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(
                    SchedulerResponse.builder()
                            .message(e.getMessage())
                            .type(schedulerType)
                            .scheduler(null)
                            .build());
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(
                    SchedulerResponse.builder()
                            .message(e.getMessage())
                            .type(schedulerType)
                            .scheduler(null)
                            .build());
        }
    }

    @DeleteMapping("/{schedulerType}/{id}")
    public ResponseEntity<SchedulerResponse> deleteScheduler(@PathVariable String schedulerType, @PathVariable int id){
        try{
            return ResponseEntity.ok(schedulerService.deleteSchedulerById(schedulerType,id));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(
                    SchedulerResponse.builder()
                            .message(e.getMessage())
                            .type(schedulerType)
                            .scheduler(null)
                            .build());
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(
                    SchedulerResponse.builder()
                            .message(e.getMessage())
                            .type(schedulerType)
                            .scheduler(null)
                            .build());
        }
    }
}
