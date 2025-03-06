package org.example.yolofarmbe.Controller;

import org.example.yolofarmbe.Entity.Reminder;
import org.example.yolofarmbe.Entity.ReminderId;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Request.ReminderRequest;
import org.example.yolofarmbe.Response.ReminderResponse;
import org.example.yolofarmbe.Service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reminders")
public class ReminderController {
    @Autowired
    private ReminderService reminderService;

    @GetMapping
    public List<Reminder> getAllReminder(){
        return reminderService.getAllReminder();
    }

    @GetMapping("/id")
    public ResponseEntity<ReminderResponse> getAReminder(@RequestBody ReminderId reminderId){
        try {
            ReminderResponse reminderResponse = reminderService.getAReminder(reminderId);
            return ResponseEntity.ok(reminderResponse);
        } catch (ResourceNotFoundException e){
            return ResponseEntity.ok(ReminderResponse.builder()
                    .message("Reminder with id " + reminderId.getId()+ " and username " +reminderId.getUsername()+ " doesn't exist!")
                    .reminder(null)
                    .build());
        }
    }

    @PostMapping
    public ResponseEntity<ReminderResponse> addAReminder(@RequestBody Reminder reminder){
        return ResponseEntity.ok(reminderService.addAReminder(reminder));
    }

    @PutMapping
    public ResponseEntity<ReminderResponse> UpdateAReminder(@RequestBody ReminderRequest reminderRequest){
        try {
            ReminderResponse reminderResponse = reminderService.updateAReminder(reminderRequest.getReminderId(), reminderRequest.getReminder());
            return ResponseEntity.ok(reminderResponse);
        } catch (ResourceNotFoundException e){
            return ResponseEntity.ok(ReminderResponse.builder()
                    .message("Reminder with id " + reminderRequest.getReminderId().getId()+ " and username " +reminderRequest.getReminderId().getUsername()+ " doesn't exist!")
                    .reminder(null)
                    .build());
        }
    }

    @DeleteMapping
    public ResponseEntity<ReminderResponse> DeleteAReminder(@RequestBody ReminderId reminderId){
        try {
            ReminderResponse reminderResponse = reminderService.deleteAReminder(reminderId);
            return ResponseEntity.ok(reminderResponse);
        } catch (ResourceNotFoundException e){
            return ResponseEntity.ok(ReminderResponse.builder()
                    .message("Reminder with id " + reminderId.getId()+ " and username " +reminderId.getUsername()+ " doesn't exist!")
                    .reminder(null)
                    .build());
        }
    }
}
