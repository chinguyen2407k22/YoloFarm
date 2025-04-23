package org.example.yolofarmbe.Service;

import org.example.yolofarmbe.DTO.ActivityLogView;
import org.example.yolofarmbe.DTO.UserView;
import org.example.yolofarmbe.Entity.ActivityLog;
import org.example.yolofarmbe.Entity.Farm;
import org.example.yolofarmbe.Entity.UserAccount;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Exception.UserNotFoundException;
import org.example.yolofarmbe.Repository.ActivityLogRepository;
import org.example.yolofarmbe.Repository.FarmRepository;
import org.example.yolofarmbe.Repository.UserAccountRepository;
import org.example.yolofarmbe.Response.ActivityLogResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ActivityLogService {
    @Autowired
    private ActivityLogRepository activityLogRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private FarmRepository farmRepository;

    public List<ActivityLogView> getAllActivityLogs(){
        return  activityLogRepository.findAllActivityLog();
    }

    public List<ActivityLogView>getAllActivityLogOfAUser(String username){
        UserAccount userAccount = userAccountRepository.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException());
        return activityLogRepository.findByUserAccountUsername(username);
    }

    public List<ActivityLogView> getAllActivityLogOfAFarm(int farm_id){
        Farm farm = farmRepository.findById(farm_id)
                .orElseThrow(()->new ResourceNotFoundException("Farm with id: "+farm_id+" doesn't exist!"));
        List<UserView> userAccountList = userAccountRepository.findAllByFarmId(farm_id);
        List<ActivityLogView> activityLogList = new ArrayList<>();;
        for (UserView userView: userAccountList){
            List<ActivityLogView> tmp = activityLogRepository.findByUserAccountUsername(userView.getUsername());
            activityLogList.addAll(tmp);
        }
        activityLogList.sort(Comparator.comparing(ActivityLogView::getLogTime));
        return activityLogList;
    }

    public ActivityLogResponse getAActivityLog(int id){
        ActivityLog activityLog = activityLogRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Activity Log with id "+id+" doesn't exits!"));
        ActivityLogView activityLogView = activityLogRepository.findActivityLogById(id)
                .orElseThrow(()->new ResourceNotFoundException("Error in find Activity Log View"));
        return ActivityLogResponse.builder()
                .message("Get activity lod sucessful!")
                .activityLog(activityLogView)
                .build();
    }

    public ActivityLogResponse addAActivityLog(ActivityLog activityLog){
        activityLogRepository.save(activityLog);
        ActivityLogView activityLogView = activityLogRepository.findActivityLogById(activityLog.getId())
                .orElseThrow(()->new ResourceNotFoundException("Error in find Activity Log View"));
        return  ActivityLogResponse.builder()
                .message("Add a new activity lod sucessful!")
                .activityLog(activityLogView)
                .build();
    }

    public ActivityLogResponse updateAActivityLog(int id, ActivityLog activityLogInfo){
        ActivityLog activityLog = activityLogRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Activity Log with id "+id+" doesn't exits!"));
        if(activityLogInfo.getLogTime()!= null){
            activityLog.setLogTime(activityLogInfo.getLogTime());
        }
        if(activityLogInfo.getUserAccount()!=null) {
            String username = activityLogInfo.getUserAccount().getUsername();
            UserAccount userAccount = userAccountRepository.findByUsername(username)
                    .orElseThrow(()->new UserNotFoundException());
            activityLog.setUserAccount(userAccount);
        }
        if(activityLogInfo.getCategory()!=null){
            activityLog.setCategory(activityLogInfo.getCategory());
        }
        if(activityLogInfo.getMode()!=null){
            activityLog.setMode(activityLogInfo.getMode());
        }
        if(activityLogInfo.getTitle()!=null){
            activityLog.setTitle(activityLogInfo.getTitle());
        }
        activityLogRepository.save(activityLog);
        ActivityLogView activityLogView = activityLogRepository.findActivityLogById(activityLog.getId())
                .orElseThrow(()->new ResourceNotFoundException("Error in find Activity Log View"));
        return  ActivityLogResponse.builder()
                .message("Update a new activity lod sucessful!")
                .activityLog(activityLogView)
                .build();
    }

    public ActivityLogResponse deleteAActivityLog(int id){
        ActivityLog activityLog = activityLogRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Activity Log with id "+id+" doesn't exits!"));
        ActivityLogView activityLogView = activityLogRepository.findActivityLogById(activityLog.getId())
                .orElseThrow(()->new ResourceNotFoundException("Error in find Activity Log View"));
        activityLogRepository.delete(activityLog);
        return  ActivityLogResponse.builder()
                .message("Delete a new activity lod sucessful!")
                .activityLog(activityLogView)
                .build();
    }
}
