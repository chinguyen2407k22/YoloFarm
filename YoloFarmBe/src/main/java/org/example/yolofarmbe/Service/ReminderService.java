package org.example.yolofarmbe.Service;

import org.example.yolofarmbe.DTO.ReminderView;
import org.example.yolofarmbe.Entity.Reminder;
import org.example.yolofarmbe.Entity.ReminderId;
import org.example.yolofarmbe.Entity.UserAccount;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Exception.UserNotFoundException;
import org.example.yolofarmbe.Repository.ReminderRepository;
import org.example.yolofarmbe.Repository.UserAccountRepository;
import org.example.yolofarmbe.Response.ReminderResponse;
import org.example.yolofarmbe.Response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReminderService {
    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    public List<Reminder> getAllReminder(){
        return reminderRepository.findAll();
    }

    public List<ReminderView> getAllReminderOfAUser(String username){
        return reminderRepository.findByUsername_Username(username);
    }

    public ReminderResponse getAReminder(ReminderId reminderId){
        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(()->new ResourceNotFoundException("Reminder with id " + reminderId.getId()+ " and username " +reminderId.getUsername()+ " doesn't exist!"));
        return ReminderResponse.builder()
                .message("Get reminder successful!")
                .reminder(reminder)
                .build();
    }

    public ReminderResponse addAReminder(Reminder reminder){
        String username = reminder.getId().getUsername();
        UserAccount userAccount = userAccountRepository.findByUsername(username)
                        .orElseThrow(()->new UserNotFoundException());
        reminder.setUsername(userAccount);
        reminderRepository.save(reminder);
        return ReminderResponse.builder()
                .message("Add new reminder successful!")
                .reminder(reminder)
                .build();
    }

    public ReminderResponse updateAReminder(ReminderId reminderId, Reminder reminderInfo){
        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(()->new ResourceNotFoundException("Reminder with id " + reminderId.getId()+ " and username " +reminderId.getUsername()+ " doesn't exist!"));
        if(reminderInfo.getIsDone()!=null){
            reminder.setIsDone(reminderInfo.getIsDone());
        }
        if(reminderInfo.getReminderDescription()!=null){
            reminder.setReminderDescription(reminderInfo.getReminderDescription());
        }
        if(reminderInfo.getReminderTime()!=null){
            reminder.setReminderTime(reminderInfo.getReminderTime());
        }
        if(reminderInfo.getTitle()!=null){
            reminder.setTitle(reminderInfo.getTitle());
        }
        reminderRepository.save(reminder);
        return ReminderResponse.builder()
                .message("Update reminder successful!")
                .reminder(reminder)
                .build();
    }

    public ReminderResponse deleteAReminder(ReminderId reminderId){
        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(()->new ResourceNotFoundException("Reminder with id " + reminderId.getId()+ " and username " +reminderId.getUsername()+ " doesn't exist!"));
        reminderRepository.delete(reminder);
        return ReminderResponse.builder()
                .message("Delete reminder successful!")
                .reminder(reminder)
                .build();
    }
}
