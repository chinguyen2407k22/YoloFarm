package org.example.yolofarmbe.Controller;

import org.example.yolofarmbe.Entity.Device;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Response.DeviceResponse;
import org.example.yolofarmbe.Service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public List<Device> getAllDevice(){
        return deviceService.getAllDevice();
    }

    @GetMapping("/farm/{id}")
    public List<Device> getAllDeviceOfAFarm(@PathVariable int id){
        return deviceService.getAllDeviceOfAFarm(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceResponse> getDeviceById(@PathVariable int id){
        try {
            return ResponseEntity.ok(deviceService.getDeviceById(id));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(
                    DeviceResponse.builder()
                            .device(null)
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @PostMapping
    public ResponseEntity<DeviceResponse> addANewDevice(@RequestBody Device device){
        try {
            return ResponseEntity.ok(deviceService.addNewDevice(device));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(
                    DeviceResponse.builder()
                            .device(null)
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceResponse> updateDevice(@PathVariable int id,@RequestBody Device device){
        try {
            return ResponseEntity.ok(deviceService.updateDevice(id,device));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(
                    DeviceResponse.builder()
                            .device(null)
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeviceResponse> deleteDevice(@PathVariable int id){
        try {
            return ResponseEntity.ok(deviceService.deleteADevice(id));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(
                    DeviceResponse.builder()
                            .device(null)
                            .message(e.getMessage())
                            .build()
            );
        }
    }
}
