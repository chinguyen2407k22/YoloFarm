package org.example.yolofarmbe.Controller;

import org.example.yolofarmbe.Entity.TemperatureSetting;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Request.TemperatureSettingRequest;
import org.example.yolofarmbe.Response.LightSettingResponse;
import org.example.yolofarmbe.Response.TemperatureSettingResponse;
import org.example.yolofarmbe.Service.TemperatureSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/temperaturesettings")
public class TemperatureSettingController {
    @Autowired
    private TemperatureSettingService temperatureSettingService;

    @GetMapping("{mode}")
    public List<? extends TemperatureSetting> getAllTemperatureSetting(@PathVariable String mode) {
        return temperatureSettingService.getAllTemperatureSetting(mode);
    }

    @GetMapping("{mode}/farm/{farm_id}")
    public ResponseEntity<TemperatureSettingResponse> getTemperatureSettingByFarmId(@PathVariable String mode,
            @PathVariable int farm_id) {
        try {
            return ResponseEntity.ok(temperatureSettingService.getTemperatureSettingByFarm(mode, farm_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(
                    TemperatureSettingResponse.builder()
                            .temperatureSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    TemperatureSettingResponse.builder()
                            .temperatureSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build());

        }
    }

    @GetMapping("{mode}/{id}")
    public ResponseEntity<TemperatureSettingResponse> getTemperatureSettingById(@PathVariable String mode,
            @PathVariable int id) {
        try {
            return ResponseEntity.ok(temperatureSettingService.getTemperatureSettingById(mode, id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(
                    TemperatureSettingResponse.builder()
                            .temperatureSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    TemperatureSettingResponse.builder()
                            .temperatureSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build());

        }
    }

    @PostMapping("/{mode}")
    public ResponseEntity<TemperatureSettingResponse> addTemperatureSetting(
            @RequestBody TemperatureSettingRequest temperatureSettingRequest, @PathVariable String mode) {
        try {
            return ResponseEntity.ok(temperatureSettingService.addTemperatureSetting(mode, temperatureSettingRequest));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(
                    TemperatureSettingResponse.builder()
                            .temperatureSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    TemperatureSettingResponse.builder()
                            .temperatureSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build());

        }
    }

    @PutMapping("/{mode}/{id}")
    public ResponseEntity<TemperatureSettingResponse> updateTemperatureSetting(
            @RequestBody TemperatureSettingRequest temperatureSettingRequest, @PathVariable String mode,
            @PathVariable int id) {
        try {
            return ResponseEntity
                    .ok(temperatureSettingService.updateTemperatureSetting(mode, id, temperatureSettingRequest));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(
                    TemperatureSettingResponse.builder()
                            .temperatureSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    TemperatureSettingResponse.builder()
                            .temperatureSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build());

        }
    }

    @DeleteMapping("/{mode}/{id}")
    public ResponseEntity<TemperatureSettingResponse> deleteTemperatureSetting(@PathVariable String mode,
            @PathVariable int id) {
        try {
            return ResponseEntity.ok(temperatureSettingService.deleteTemperatureSetting(mode, id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(
                    TemperatureSettingResponse.builder()
                            .temperatureSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    TemperatureSettingResponse.builder()
                            .temperatureSetting(null)
                            .message(e.getMessage())
                            .mode(mode)
                            .build());

        }
    }
}
