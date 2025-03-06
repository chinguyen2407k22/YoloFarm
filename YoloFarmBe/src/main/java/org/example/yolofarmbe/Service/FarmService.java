package org.example.yolofarmbe.Service;

import org.example.yolofarmbe.Entity.Farm;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Repository.FarmRepository;
import org.example.yolofarmbe.Response.FarmResponse;
import org.example.yolofarmbe.Response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmService {
    @Autowired
    FarmRepository farmRepository;

    public List<Farm> getAllFarm(){
        return farmRepository.findAll();
    }

    public FarmResponse getFarmById(int id){
        Farm farm = farmRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Farm with id " + id + " does not exist"));
        return FarmResponse.builder()
                .message("Get farm's information successfully!")
                .farm(farm)
                .build();
    }

    public FarmResponse addNewFarm(Farm farm){
        farmRepository.save(farm);
        return FarmResponse.builder()
                .message("Add new farm successfully!")
                .farm(farm)
                .build();
    }

    public FarmResponse updateAFarm(int id, Farm farmifo){
        Farm farm = farmRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Farm with id " + id + " does not exist"));
        if(farmifo.getCrop() != null){
            farm.setCrop(farmifo.getCrop());
        }
        if(farmifo.getFarmSize() != null){
            farm.setFarmSize(farmifo.getFarmSize());
        }
        if(farmifo.getFarmName()!= null) {
            farm.setFarmName(farmifo.getFarmName());
        }
        farmRepository.save(farm);
        return FarmResponse.builder()
                .message("Update fam's information successfully for farm wih id " + id)
                .farm(farm)
                .build();
    }

    public FarmResponse deleteAFarm(int id){
        Farm farm = farmRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Farm with id " + id + " does not exist"));
        farmRepository.delete(farm);
        return FarmResponse.builder()
                .message("Delete farm with id "+ id + " successfully!")
                .farm(farm)
                .build();
    }
}
