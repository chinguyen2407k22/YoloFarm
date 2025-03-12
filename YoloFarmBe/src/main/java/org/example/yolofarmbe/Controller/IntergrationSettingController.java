package org.example.yolofarmbe.Controller;

import org.example.yolofarmbe.Entity.IntergrationSetting;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Response.IntergrationSettingResponse;
import org.example.yolofarmbe.Service.IntergrationSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/intergrationsettings")
public class IntergrationSettingController {
    @Autowired
    IntergrationSettingService intergrationSettingService;

    @GetMapping
    public List<IntergrationSetting> getAllIntergartionSetting(){
        return intergrationSettingService.getAllIntergrationSettings();
    }

    @GetMapping("/farm/{id}")
    public List<IntergrationSetting> getIntergrationSettingByFarm(@PathVariable int id){
        try {
            return intergrationSettingService.getIntergrationSettingByFarm(id);
        } catch (ResourceNotFoundException e){
            return null;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<IntergrationSettingResponse> getIntergrationSettingbyid(@PathVariable int id){
        try {
            IntergrationSettingResponse intergrationSettingResponse = intergrationSettingService.getIntergrationSettingById(id);
            return ResponseEntity.ok(intergrationSettingResponse);
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(
                    IntergrationSettingResponse.builder()
                            .message(e.getMessage())
                            .intergrationSetting(null)
                            .build());
        }
    }

    @PostMapping
    public ResponseEntity<IntergrationSettingResponse> addIntergrationSetting(@RequestBody IntergrationSetting intergrationSetting){
        IntergrationSettingResponse intergrationSettingResponse = intergrationSettingService.addIntergrationSettingBy(intergrationSetting);
        return ResponseEntity.ok(intergrationSettingResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IntergrationSettingResponse> updateIntergrationSetting(@PathVariable int id, @RequestBody IntergrationSetting intergrationSetting){
        try {
            IntergrationSettingResponse intergrationSettingResponse = intergrationSettingService.updateIntergrationSettingBy(id,intergrationSetting);
            return ResponseEntity.ok(intergrationSettingResponse);
        } catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(
                    IntergrationSettingResponse.builder()
                            .message(e.getMessage())
                            .intergrationSetting(null)
                            .build());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<IntergrationSettingResponse> deleteIntergrationSettingbyid(@PathVariable int id){
        try {
            IntergrationSettingResponse intergrationSettingResponse = intergrationSettingService.deleteIntergrationSettingById(id);
            return ResponseEntity.ok(intergrationSettingResponse);
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(
                    IntergrationSettingResponse.builder()
                            .message(e.getMessage())
                            .intergrationSetting(null)
                            .build());
        }
    }

}
