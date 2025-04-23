package org.example.yolofarmbe.Service;

import org.example.yolofarmbe.DTO.ReminderView;
import org.example.yolofarmbe.DTO.UserView;
import org.example.yolofarmbe.Entity.*;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Exception.UserNotFoundException;
import org.example.yolofarmbe.Repository.FarmRepository;
import org.example.yolofarmbe.Repository.ReminderRepository;
import org.example.yolofarmbe.Repository.UserAccountRepository;
import org.example.yolofarmbe.Response.ReminderResponse;
import org.example.yolofarmbe.Response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ReminderService {
    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private FarmRepository farmRepository;

    public List<ReminderView> getAllReminderOfAFarm(int farm_id){
        Farm farm = farmRepository.findById(farm_id)
                .orElseThrow(()->new ResourceNotFoundException("Farm with id: "+farm_id+" doesn't exist!"));
        List<UserView> userAccountList = userAccountRepository.findAllByFarmId(farm_id);
        List<ReminderView> reminderViewList = new ArrayList<>();;
        for (UserView userView: userAccountList){
            List<ReminderView> tmp = reminderRepository.findByUserAccount_Username(userView.getUsername());
            reminderViewList.addAll(tmp);
        }
        reminderViewList.sort(Comparator.comparing(ReminderView::getReminderTime));
        return reminderViewList;
    }

    public List<ReminderView> getAllReminderOfAUser(String username){
        return reminderRepository.findByUserAccount_Username(username);
    }

    public ReminderResponse getAReminder(ReminderId reminderId){
        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(()->new ResourceNotFoundException("Reminder with id " + reminderId.getId()+ " and username " +reminderId.getUsername()+ " doesn't exist!"));
        ReminderView reminderView = reminderRepository.findReminderById(reminder.getId())
                .orElseThrow(()->new ResourceNotFoundException("Error in Get Reminder View!"));
        return ReminderResponse.builder()
                .message("Get reminder successful!")
                .reminder(reminderView)
                .build();
    }

    public ReminderResponse addAReminder(Reminder reminder){
        String username = reminder.getId().getUsername();
        UserAccount userAccount = userAccountRepository.findByUsername(username)
                        .orElseThrow(()->new UserNotFoundException());
        reminder.setUserAccount(userAccount);
        reminderRepository.save(reminder);
        ReminderView reminderView = reminderRepository.findReminderById(reminder.getId())
                .orElseThrow(()->new ResourceNotFoundException("Error in Get Reminder View!"));
        return ReminderResponse.builder()
                .message("Add new reminder successful!")
                .reminder(reminderView)
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
        ReminderView reminderView = reminderRepository.findReminderById(reminder.getId())
                .orElseThrow(()->new ResourceNotFoundException("Error in Get Reminder View!"));
        return ReminderResponse.builder()
                .message("Update reminder successful!")
                .reminder(reminderView)
                .build();
    }

    public ReminderResponse deleteAReminder(ReminderId reminderId){
        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(()->new ResourceNotFoundException("Reminder with id " + reminderId.getId()+ " and username " +reminderId.getUsername()+ " doesn't exist!"));
        ReminderView reminderView = reminderRepository.findReminderById(reminder.getId())
                .orElseThrow(()->new ResourceNotFoundException("Error in Get Reminder View!"));
        reminderRepository.delete(reminder);
        return ReminderResponse.builder()
                .message("Delete reminder successful!")
                .reminder(reminderView)
                .build();
    }
}
