package org.example.yolofarmbe.Service;

import org.example.yolofarmbe.Entity.ActivityLog;
import org.example.yolofarmbe.Entity.Farm;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Repository.ActivityLogRepository;
import org.example.yolofarmbe.Repository.FarmRepository;
import org.example.yolofarmbe.Response.ActivityLogResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityLogService {
    @Autowired
    private ActivityLogRepository activityLogRepository;

    @Autowired
    private FarmRepository farmRepository;

    public List<ActivityLog> getAllActivityLogs(){
        return  activityLogRepository.findAll();
    }

    public List<ActivityLog>getAllActivityLogOfAFarm(int farm_id){
        Farm farm = farmRepository.findById(farm_id)
                .orElseThrow(()->new ResourceNotFoundException("Farm with id " + farm_id + " doesn't exist!"));
        int id = farm.getId();
        return activityLogRepository.findByFarm_Id(farm_id);
    }

    public ActivityLogResponse getAActivityLog(int id){
        ActivityLog activityLog = activityLogRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Activity Log with id "+id+" doesn't exits!"));
        return ActivityLogResponse.builder()
                .message("Get activity lod sucessful!")
                .activityLog(activityLog)
                .build();
    }

    public ActivityLogResponse addAActivityLog(ActivityLog activityLog){
        activityLogRepository.save(activityLog);
        return  ActivityLogResponse.builder()
                .message("Add a new activity lod sucessful!")
                .activityLog(activityLog)
                .build();
    }

    public ActivityLogResponse updateAActivityLog(int id, ActivityLog activityLogInfo){
        ActivityLog activityLog = activityLogRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Activity Log with id "+id+" doesn't exits!"));
        if(activityLogInfo.getLogTime()!= null){
            activityLog.setLogTime(activityLogInfo.getLogTime());
        }
        if(activityLogInfo.getFarm()!=null) {
            int farm_id = activityLogInfo.getFarm().getId();
            Farm farm = farmRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Farm with id " + farm_id + " doesn't exist!"));
            activityLog.setFarm(farm);
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
        return  ActivityLogResponse.builder()
                .message("Update a new activity lod sucessful!")
                .activityLog(activityLog)
                .build();
    }

    public ActivityLogResponse deleteAActivityLog(int id){
        ActivityLog activityLog = activityLogRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Activity Log with id "+id+" doesn't exits!"));
        activityLogRepository.delete(activityLog);
        return  ActivityLogResponse.builder()
                .message("Delete a new activity lod sucessful!")
                .activityLog(activityLog)
                .build();
    }
}
