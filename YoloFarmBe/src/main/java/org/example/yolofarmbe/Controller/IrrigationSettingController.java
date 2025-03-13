package org.example.yolofarmbe.Controller;

import org.example.yolofarmbe.Entity.IrrigationSetting;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Request.IrrigationSettingRequest;
import org.example.yolofarmbe.Response.IrrigationSettingResponse;
import org.example.yolofarmbe.Response.TemperatureSettingResponse;
import org.example.yolofarmbe.Service.IrrigationSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/irrigationsettings")
public class IrrigationSettingController {
    @Autowired
    private IrrigationSettingService irrigationSettingService;

    @GetMapping("/{mode}")
    public List<? extends IrrigationSetting> getAllIrrigationSetting(@PathVariable String mode){
        return irrigationSettingService.getAllIrigationSetting(mode);
    }

    @GetMapping("/{mode}/farm/{farm_id}")
    public ResponseEntity<IrrigationSettingResponse> getIrrigationSettingByFarm(@PathVariable String mode, @PathVariable int farm_id){
        try {
            return ResponseEntity.ok(irrigationSettingService.getAllIrigationSettingByFarm(mode,farm_id));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(
                    IrrigationSettingResponse.builder()
                            .irrigationSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build()
            );
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(
                    IrrigationSettingResponse.builder()
                            .irrigationSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build());
        }
    }

    @GetMapping("/{mode}/{id}")
    public ResponseEntity<IrrigationSettingResponse> getIrrigationSettingById(@PathVariable String mode, @PathVariable int id){
        try {
            return ResponseEntity.ok(irrigationSettingService.getAllIrigationSettingById(mode,id));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(
                    IrrigationSettingResponse.builder()
                            .irrigationSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build()
            );
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(
                    IrrigationSettingResponse.builder()
                            .irrigationSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build());
        }
    }

    @PostMapping("/{mode}")
    public ResponseEntity<IrrigationSettingResponse> addIrrigationSettingById(@PathVariable String mode, @RequestBody IrrigationSettingRequest irrigatingSettingRequest){
        try {
            return ResponseEntity.ok(irrigationSettingService.addAIrigationSetting(mode, irrigatingSettingRequest));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(
                    IrrigationSettingResponse.builder()
                            .irrigationSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build()
            );
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(
                    IrrigationSettingResponse.builder()
                            .irrigationSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build());
        }
    }

    @PutMapping("/{mode}/{id}")
    public ResponseEntity<IrrigationSettingResponse> updateIrrigationSettingById(@PathVariable String mode, @PathVariable int id, @RequestBody IrrigationSettingRequest irrigatingSettingRequest){
        try {
            return ResponseEntity.ok(irrigationSettingService.updateAIrigationSetting(mode,id, irrigatingSettingRequest));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(
                    IrrigationSettingResponse.builder()
                            .irrigationSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build()
            );
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(
                    IrrigationSettingResponse.builder()
                            .irrigationSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build());
        }
    }

    @DeleteMapping("/{mode}/{id}")
    public ResponseEntity<IrrigationSettingResponse> deleteIrrigationSettingById(@PathVariable String mode, @PathVariable int id){
        try {
            return ResponseEntity.ok(irrigationSettingService.deleteAIrigationSetting(mode, id));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(
                    IrrigationSettingResponse.builder()
                            .irrigationSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build()
            );
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(
                    IrrigationSettingResponse.builder()
                            .irrigationSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build());
        }
    }

}
