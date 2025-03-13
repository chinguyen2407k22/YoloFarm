package org.example.yolofarmbe.Service;

import org.example.yolofarmbe.Entity.Device;
import org.example.yolofarmbe.Entity.Farm;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Repository.DeviceRepository;
import org.example.yolofarmbe.Repository.FarmRepository;
import org.example.yolofarmbe.Response.DeviceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private FarmRepository farmRepository;

    public List<Device> getAllDevice(){
        return deviceRepository.findAll();
    }

    public List<Device> getAllDeviceOfAFarm(int farm_id){
        return deviceRepository.findByFarm_Id(farm_id);
    }

    public DeviceResponse getDeviceById(int id){
        Device device = deviceRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Device with id "+ id+" doesn't exists!"));
        return DeviceResponse.builder()
                .message("Get Device with id "+id+" successfully!")
                .device(device)
                .build();
    }

    public DeviceResponse addNewDevice(Device device){
        if(device.getFarm()!=null){
            int id = device.getFarm().getId();
            Farm farm = farmRepository.findById(id)
                    .orElseThrow(()->new ResourceNotFoundException("Farm with id "+id+"doesn't exist!"));
            device.setFarm(farm);
        }
        deviceRepository.save(device);
        return DeviceResponse.builder()
                .message("Add new device successfully!")
                .device(device)
                .build();
    }

    public DeviceResponse updateDevice(int id, Device deviceInfo){
        Device device = deviceRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Device with id "+ id+" doesn't exists!"));
        if(deviceInfo.getLocation()!=null){
            device.setLocation(deviceInfo.getLocation());
        }
        if(deviceInfo.getModel()!=null){
            device.setModel(deviceInfo.getModel());
        }
        if(deviceInfo.getName()!=null){
            device.setName(deviceInfo.getName());
        }
        if(deviceInfo.getState()!=null){
            device.setState(deviceInfo.getState());
        }
        if(deviceInfo.getType()!=null){
            device.setType(deviceInfo.getType());
        }
        if(device.getFarm()!=null){
            int farm_id = device.getFarm().getId();
            Farm farm = farmRepository.findById(farm_id)
                    .orElseThrow(()->new ResourceNotFoundException("Farm with id "+farm_id+"doesn't exist!"));
            device.setFarm(farm);
        }
        deviceRepository.save(device);
        return DeviceResponse.builder()
                .message("Update device successfully!")
                .device(device)
                .build();
    }

    public DeviceResponse deleteADevice(int id){
        Device device = deviceRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Device with id "+ id+" doesn't exists!"));
        deviceRepository.save(device);
        return DeviceResponse.builder()
                .message("Delete device successfully!")
                .device(device)
                .build();
    }
}
