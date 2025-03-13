package org.example.yolofarmbe.Controller;

import org.example.yolofarmbe.Entity.LightSetting;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Request.LightSettingRequest;
import org.example.yolofarmbe.Response.LightSettingResponse;
import org.example.yolofarmbe.Response.SchedulerResponse;
import org.example.yolofarmbe.Service.LightSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lightsettings")
public class LightSettingController {
    @Autowired
    private LightSettingService lightSettingService;

    @GetMapping("/{mode}")
    public List<? extends LightSetting> getAllLightSetting(@PathVariable String mode){
        return lightSettingService.getAllLightSetting(mode);
    }

    @GetMapping("/farm/{mode}/{farm_id}")
    public ResponseEntity<LightSettingResponse> getLightSettingByFarmId(@PathVariable String mode, @PathVariable int farm_id){
        try {
            return ResponseEntity.ok(lightSettingService.getAllLightSettingFarm(mode, farm_id));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(
                    LightSettingResponse.builder()
                            .lightSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build()
            );
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(
                    LightSettingResponse.builder()
                            .lightSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build());
        }
    }

    @GetMapping("/{mode}/{id}")
    public ResponseEntity<LightSettingResponse> getLightSettingByID(@PathVariable String mode, @PathVariable int id){
        try {
            return ResponseEntity.ok(lightSettingService.getLightSettingById(mode, id));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(
                    LightSettingResponse.builder()
                            .lightSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build()
            );
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(
                    LightSettingResponse.builder()
                            .lightSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build());
        }
    }

    @PostMapping("/{mode}")
    public ResponseEntity<LightSettingResponse> addLightSetting(@PathVariable String mode, @RequestBody LightSettingRequest lightSettingRequest){
        try {
            return ResponseEntity.ok(lightSettingService.addALightSetting(lightSettingRequest,mode));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(
                    LightSettingResponse.builder()
                            .lightSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build()
            );
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(
                    LightSettingResponse.builder()
                            .lightSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build());
        }
    }

    @PutMapping("/{mode}/{id}")
    public ResponseEntity<LightSettingResponse> updateLightSetting(@PathVariable String mode, @PathVariable int id, @RequestBody LightSettingRequest lightSettingRequest){
        try {
            return ResponseEntity.ok(lightSettingService.updateALightSetting(lightSettingRequest, mode, id));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(
                    LightSettingResponse.builder()
                            .lightSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build()
            );
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(
                    LightSettingResponse.builder()
                            .lightSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build());
        }
    }

    @DeleteMapping("/{mode}/{id}")
    public ResponseEntity<LightSettingResponse> deleteLightSetting(@PathVariable String mode, @PathVariable int id){
        try {
            return ResponseEntity.ok(lightSettingService.deleteLightSetting(mode,id));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(
                    LightSettingResponse.builder()
                            .lightSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build()
            );
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(
                    LightSettingResponse.builder()
                            .lightSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build());
        }
    }
}
