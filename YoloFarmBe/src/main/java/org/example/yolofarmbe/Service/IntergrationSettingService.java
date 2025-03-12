package org.example.yolofarmbe.Service;

import org.example.yolofarmbe.Entity.Farm;
import org.example.yolofarmbe.Entity.IntergrationSetting;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Repository.FarmRepository;
import org.example.yolofarmbe.Repository.IntergrationSettingRepository;
import org.example.yolofarmbe.Response.IntergrationSettingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntergrationSettingService {
    @Autowired
    private IntergrationSettingRepository intergrationSettingRepository;

    @Autowired
    private FarmRepository farmRepository;

    public List<IntergrationSetting> getAllIntergrationSettings() {
        return intergrationSettingRepository.findAll();
    }

    public List<IntergrationSetting> getIntergrationSettingByFarm(int id){
        Farm farm = farmRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Farm with id " + id + " does not exist!"));
        return  intergrationSettingRepository.findByFarm_Id(id);
    }

    public IntergrationSettingResponse getIntergrationSettingById(int id){
        IntergrationSetting intergrationSetting = intergrationSettingRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Intergration Setting with id " + id + " does not exist!"));
        return IntergrationSettingResponse.builder()
                .message("Get intergration setting successfully")
                .intergrationSetting(intergrationSetting)
                .build();
    }

    public IntergrationSettingResponse addIntergrationSettingBy(IntergrationSetting intergrationSetting){
        if (intergrationSetting.getFarm() != null){
            int id = intergrationSetting.getFarm().getId();
            Farm farm = farmRepository.findById(id)
                    .orElseThrow(()-> new ResourceNotFoundException("Farm with id " + id + " does not exist!"));
            intergrationSetting.setFarm(farm);
        }
        intergrationSettingRepository.save(intergrationSetting);
        return IntergrationSettingResponse.builder()
                .message("Add intergration setting successfully")
                .intergrationSetting(intergrationSetting)
                .build();
    }

    public IntergrationSettingResponse updateIntergrationSettingBy(int id, IntergrationSetting info){
        IntergrationSetting intergrationSetting = intergrationSettingRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Intergration Setting with id " + id + " does not exist!"));
        if(info.getFarm()!=null){
            int farm_id = info.getFarm().getId();
            Farm farm = farmRepository.findById(id)
                    .orElseThrow(()-> new ResourceNotFoundException("Farm with id " + id + " does not exist!"));
            intergrationSetting.setFarm(farm);
        }
        if(info.getPredict()!=null){
            intergrationSetting.setPredict(info.getPredict());
        }
        if(info.getSoilSensor()!= null){
            intergrationSetting.setSoilSensor(info.getSoilSensor());
        }
        if(info.getWeatherApi()!=null){
            intergrationSetting.setWeatherApi(info.getWeatherApi());
        }
        intergrationSettingRepository.save(intergrationSetting);
        return IntergrationSettingResponse.builder()
                .message("Update intergration setting successfully")
                .intergrationSetting(intergrationSetting)
                .build();
    }
    public IntergrationSettingResponse deleteIntergrationSettingById(int id){
        IntergrationSetting intergrationSetting = intergrationSettingRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Intergration Setting with id " + id + " does not exist!"));
        intergrationSettingRepository.delete(intergrationSetting);
        return IntergrationSettingResponse.builder()
                .message("Delete intergration setting successfully")
                .intergrationSetting(intergrationSetting)
                .build();
    }
}
