package org.example.yolofarmbe.Service;

import org.example.yolofarmbe.Entity.LightSetting;
import org.example.yolofarmbe.Factory.LightSettingFactory;
import org.example.yolofarmbe.Repository.LightAutomatedRepository;
import org.example.yolofarmbe.Repository.LightManualRepository;
import org.example.yolofarmbe.Repository.LightScheduledRepository;
import org.example.yolofarmbe.Response.LightSettingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LightSettingService {
    @Autowired
    private LightSettingFactory lightSettingFactory;

    public List<? extends LightSetting> getAllLightSetting(String mode){
        switch (mode.toLowerCase()){
            case "automated":
                LightAutomatedRepository lightAutomatedRepository = lightSettingFactory.getLightSettingRepository(LightAutomatedRepository.class);
                return lightAutomatedRepository.findAll();
            case "manual":
                LightManualRepository lightManualRepository = lightSettingFactory.getLightSettingRepository(LightManualRepository.class);
                return lightManualRepository.findAll();
            case "scheduled":
                LightScheduledRepository lightScheduledRepository = lightSettingFactory.getLightSettingRepository(LightScheduledRepository.class);
                return lightScheduledRepository.findAll();
            default:
                throw new IllegalArgumentException("Invalid light setting mode: " + mode);
        }
    }
}
