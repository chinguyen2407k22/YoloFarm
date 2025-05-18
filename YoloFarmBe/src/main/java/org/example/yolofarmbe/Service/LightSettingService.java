package org.example.yolofarmbe.Service;

import org.example.yolofarmbe.Entity.*;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Factory.LightSettingFactory;
import org.example.yolofarmbe.Repository.FarmRepository;
import org.example.yolofarmbe.Repository.LightAutomatedRepository;
import org.example.yolofarmbe.Repository.LightManualRepository;
import org.example.yolofarmbe.Repository.LightScheduledRepository;
import org.example.yolofarmbe.Request.LightSettingRequest;
import org.example.yolofarmbe.Response.LightSettingResponse;
import org.example.yolofarmbe.Response.SchedulerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LightSettingService {
        @Autowired
        private LightSettingFactory lightSettingFactory;

        @Autowired
        private FarmRepository farmRepository;

        @Autowired
        private SchedulerService schedulerService;

        @Autowired
        private MqttService mqttService;

        public List<? extends LightSetting> getAllLightSetting(String mode) {
                switch (mode.toLowerCase()) {
                        case "automated":
                                LightAutomatedRepository lightAutomatedRepository = lightSettingFactory
                                                .getLightSettingRepository(LightAutomatedRepository.class);
                                return lightAutomatedRepository.findAll();
                        case "manual":
                                LightManualRepository lightManualRepository = lightSettingFactory
                                                .getLightSettingRepository(LightManualRepository.class);
                                return lightManualRepository.findAll();
                        case "scheduled":
                                LightScheduledRepository lightScheduledRepository = lightSettingFactory
                                                .getLightSettingRepository(LightScheduledRepository.class);
                                return lightScheduledRepository.findAll();
                        default:
                                throw new IllegalArgumentException("Invalid light setting mode: " + mode);
                }
        }

        public LightSettingResponse<? extends LightSetting> getAllLightSettingFarm(String mode, int farm_id) {
                Farm farm = farmRepository.findById(farm_id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Light Setting with " + farm_id + "doesn't exist!"));
                switch (mode.toLowerCase()) {
                        case "automated":
                                LightAutomatedRepository lightAutomatedRepository = lightSettingFactory
                                                .getLightSettingRepository(LightAutomatedRepository.class);
                                LightAutomated lightAutomated = lightAutomatedRepository.findByFarm_Id(farm_id);
                                return LightSettingResponse.builder()
                                                .lightSetting(lightAutomated)
                                                .message("Get light setting successfully!")
                                                .mode(mode)
                                                .build();
                        case "manual":
                                LightManualRepository lightManualRepository = lightSettingFactory
                                                .getLightSettingRepository(LightManualRepository.class);
                                LightManual lightManual = lightManualRepository.findByFarm_Id(farm_id);
                                return LightSettingResponse.builder()
                                                .lightSetting(lightManual)
                                                .message("Get light setting successfully!")
                                                .mode(mode)
                                                .build();
                        case "scheduled":
                                LightScheduledRepository lightScheduledRepository = lightSettingFactory
                                                .getLightSettingRepository(LightScheduledRepository.class);
                                LightScheduled lightScheduled = lightScheduledRepository.findByFarm_Id(farm_id);
                                return LightSettingResponse.builder()
                                                .lightSetting(lightScheduled)
                                                .message("Get light setting successfully!")
                                                .mode(mode)
                                                .build();
                        default:
                                throw new IllegalArgumentException("Invalid light setting mode: " + mode);
                }
        }

        public LightSettingResponse<? extends LightSetting> getLightSettingById(String mode, int id) {
                switch (mode.toLowerCase()) {
                        case "automated":
                                LightAutomatedRepository lightAutomatedRepository = lightSettingFactory
                                                .getLightSettingRepository(LightAutomatedRepository.class);
                                LightAutomated lightAutomated = lightAutomatedRepository.findById(id)
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                                "Light Setting with id " + id + "doesn't exist!"));
                                return LightSettingResponse.builder()
                                                .lightSetting(lightAutomated)
                                                .message("Get light setting successfully!")
                                                .mode(mode)
                                                .build();
                        case "manual":
                                LightManualRepository lightManualRepository = lightSettingFactory
                                                .getLightSettingRepository(LightManualRepository.class);
                                LightManual lightManual = lightManualRepository.findById(id)
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                                "Light Setting with id " + id + "doesn't exist!"));
                                return LightSettingResponse.builder()
                                                .lightSetting(lightManual)
                                                .message("Get light setting successfully!")
                                                .mode(mode)
                                                .build();
                        case "scheduled":
                                LightScheduledRepository lightScheduledRepository = lightSettingFactory
                                                .getLightSettingRepository(LightScheduledRepository.class);
                                LightScheduled lightScheduled = lightScheduledRepository.findById(id)
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                                "Light Setting with id " + id + "doesn't exist!"));
                                return LightSettingResponse.builder()
                                                .lightSetting(lightScheduled)
                                                .message("Get light setting successfully!")
                                                .mode(mode)
                                                .build();
                        default:
                                throw new IllegalArgumentException("Invalid light setting mode: " + mode);
                }
        }

        public LightSettingResponse<? extends LightSetting> addALightSetting(LightSettingRequest lightSettingRequest,
                        String mode) {
                switch (mode.toLowerCase()) {
                        case "automated":
                                LightAutomatedRepository lightAutomatedRepository = lightSettingFactory
                                                .getLightSettingRepository(LightAutomatedRepository.class);
                                LightAutomated lightAutomated = new LightAutomated();
                                if (lightSettingRequest.getFarm() != null) {
                                        int farm_id = lightSettingRequest.getFarm().getId();
                                        Farm farm = farmRepository.findById(farm_id)
                                                        .orElseThrow(() -> new ResourceNotFoundException(
                                                                        "Light Setting with " + farm_id
                                                                                        + "doesn't exist!"));
                                        lightAutomated.setFarm(farm);
                                }
                                if (lightSettingRequest.getLower() >= 0) {
                                        lightAutomated.setLower(lightSettingRequest.getLower());
                                }
                                if (lightSettingRequest.getMin() >= 0) {
                                        lightAutomated.setMin(lightSettingRequest.getMin());
                                }
                                if (lightSettingRequest.getUpper() >= 0) {
                                        lightAutomated.setUpper(lightSettingRequest.getUpper());
                                }
                                if (lightSettingRequest.getMax() >= 0) {
                                        lightAutomated.setMax(lightSettingRequest.getMax());
                                }
                                if (lightSettingRequest.getSendWarning() != null) {
                                        lightAutomated.setSendWarning(lightSettingRequest.getSendWarning());
                                }
                                mqttService.TurnOffDeviceFanAuto();
                                mqttService.TurnOnDeviceFanAuto(
                                                lightSettingRequest.getLower(), lightSettingRequest.getUpper());
                                lightAutomatedRepository.save(lightAutomated);
                                return LightSettingResponse.builder()
                                                .mode(mode)
                                                .message("Add a new light setting successful!")
                                                .lightSetting(lightAutomated)
                                                .build();
                        case "manual":
                                LightManualRepository lightManualRepository = lightSettingFactory
                                                .getLightSettingRepository(LightManualRepository.class);
                                LightManual lightManual = new LightManual();
                                if (lightSettingRequest.getFarm() != null) {
                                        int farm_id = lightSettingRequest.getFarm().getId();
                                        Farm farm = farmRepository.findById(farm_id)
                                                        .orElseThrow(() -> new ResourceNotFoundException(
                                                                        "Light Setting with " + farm_id
                                                                                        + "doesn't exist!"));
                                        lightManual.setFarm(farm);
                                }
                                if (lightSettingRequest.getTurnOn() != null) {
                                        lightManual.setTurnOn(lightSettingRequest.getTurnOn());
                                        State state = lightSettingRequest.getTurnOn();
                                        if (state == State.ON) {
                                                mqttService.TurnOnDeviceManual("fan", "1");
                                        } else {
                                                mqttService.TurnOnDeviceManual("fan", "0");
                                        }
                                }
                                lightManualRepository.save(lightManual);
                                return LightSettingResponse.builder()
                                                .mode(mode)
                                                .message("Add a new light setting successful!")
                                                .lightSetting(lightManual)
                                                .build();
                        case "scheduled":
                                LightScheduledRepository lightScheduledRepository = lightSettingFactory
                                                .getLightSettingRepository(LightScheduledRepository.class);
                                LightScheduled lightScheduled = new LightScheduled();
                                if (lightSettingRequest.getFarm() != null) {
                                        int farm_id = lightSettingRequest.getFarm().getId();
                                        Farm farm = farmRepository.findById(farm_id)
                                                        .orElseThrow(() -> new ResourceNotFoundException(
                                                                        "Light Setting with " + farm_id
                                                                                        + "doesn't exist!"));
                                        lightScheduled.setFarm(farm);
                                }
                                if (lightSettingRequest.getSendWarning() != null) {
                                        lightScheduled.setSendWarning(lightSettingRequest.getSendWarning());
                                }
                                lightScheduledRepository.save(lightScheduled);
                                return LightSettingResponse.builder()
                                                .lightSetting(lightScheduled)
                                                .message("Add a new light setting successfully!")
                                                .mode(mode)
                                                .build();
                        default:
                                throw new IllegalArgumentException("Invalid light setting mode: " + mode);
                }
        }

        public LightSettingResponse<? extends LightSetting> updateALightSetting(LightSettingRequest lightSettingRequest,
                        String mode, int id) {
                switch (mode.toLowerCase()) {
                        case "automated":
                                LightAutomatedRepository lightAutomatedRepository = lightSettingFactory
                                                .getLightSettingRepository(LightAutomatedRepository.class);
                                LightAutomated lightAutomated = lightAutomatedRepository.findById(id)
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                                "Light Setting with id " + id + "doesn't exist!"));
                                if (lightSettingRequest.getFarm() != null) {
                                        int farm_id = lightSettingRequest.getFarm().getId();
                                        Farm farm = farmRepository.findById(farm_id)
                                                        .orElseThrow(() -> new ResourceNotFoundException(
                                                                        "Light Setting with " + farm_id
                                                                                        + "doesn't exist!"));
                                        lightAutomated.setFarm(farm);
                                }
                                if (lightSettingRequest.getLower() >= 0) {
                                        lightAutomated.setLower(lightSettingRequest.getLower());
                                }
                                if (lightSettingRequest.getMin() >= 0) {
                                        lightAutomated.setMin(lightSettingRequest.getMin());
                                }
                                if (lightSettingRequest.getUpper() >= 0) {
                                        lightAutomated.setUpper(lightSettingRequest.getUpper());
                                }
                                if (lightSettingRequest.getMax() >= 0) {
                                        lightAutomated.setMax(lightSettingRequest.getMax());
                                }
                                if (lightSettingRequest.getSendWarning() != null) {
                                        lightAutomated.setSendWarning(lightSettingRequest.getSendWarning());
                                }
                                mqttService.TurnOffDeviceFanAuto();
                                mqttService.TurnOnDeviceFanAuto(lightSettingRequest.getLower(),
                                                lightSettingRequest.getUpper());
                                lightAutomatedRepository.save(lightAutomated);
                                return LightSettingResponse.builder()
                                                .mode(mode)
                                                .message("Update light setting successful!")
                                                .lightSetting(lightAutomated)
                                                .build();
                        case "manual":
                                LightManualRepository lightManualRepository = lightSettingFactory
                                                .getLightSettingRepository(LightManualRepository.class);
                                LightManual lightManual = lightManualRepository.findById(id)
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                                "Light Setting with id " + id + "doesn't exist!"));
                                if (lightSettingRequest.getFarm() != null) {
                                        int farm_id = lightSettingRequest.getFarm().getId();
                                        Farm farm = farmRepository.findById(farm_id)
                                                        .orElseThrow(() -> new ResourceNotFoundException(
                                                                        "Light Setting with " + farm_id
                                                                                        + "doesn't exist!"));
                                        lightManual.setFarm(farm);
                                }
                                if (lightSettingRequest.getTurnOn() != null) {
                                        lightManual.setTurnOn(lightManual.getTurnOn());
                                        State state = lightSettingRequest.getTurnOn();
                                        if (state == State.ON) {
                                                mqttService.TurnOnDeviceManual("fan", "1");
                                        } else {
                                                mqttService.TurnOnDeviceManual("fan", "0");
                                        }
                                }
                                lightManualRepository.save(lightManual);
                                return LightSettingResponse.builder()
                                                .mode(mode)
                                                .message("Update light setting successful!")
                                                .lightSetting(lightManual)
                                                .build();
                        case "scheduled":
                                LightScheduledRepository lightScheduledRepository = lightSettingFactory
                                                .getLightSettingRepository(LightScheduledRepository.class);
                                LightScheduled lightScheduled = lightScheduledRepository.findById(id)
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                                "Light Setting with id " + id + "doesn't exist!"));
                                if (lightSettingRequest.getFarm() != null) {
                                        int farm_id = lightSettingRequest.getFarm().getId();
                                        Farm farm = farmRepository.findById(farm_id)
                                                        .orElseThrow(() -> new ResourceNotFoundException(
                                                                        "Light Setting with " + farm_id
                                                                                        + "doesn't exist!"));
                                        lightScheduled.setFarm(farm);
                                }
                                if (lightSettingRequest.getSendWarning() != null) {
                                        lightScheduled.setSendWarning(lightSettingRequest.getSendWarning());
                                }
                                lightScheduledRepository.save(lightScheduled);
                                return LightSettingResponse.builder()
                                                .lightSetting(lightScheduled)
                                                .message("Update light setting successfully!")
                                                .mode(mode)
                                                .build();
                        default:
                                throw new IllegalArgumentException("Invalid light setting mode: " + mode);
                }
        }

        public LightSettingResponse<? extends LightSetting> deleteLightSetting(String mode, int id) {
                switch (mode.toLowerCase()) {
                        case "automated":
                                LightAutomatedRepository lightAutomatedRepository = lightSettingFactory
                                                .getLightSettingRepository(LightAutomatedRepository.class);
                                LightAutomated lightAutomated = lightAutomatedRepository.findById(id)
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                                "Light Setting with id " + id + "doesn't exist!"));
                                lightAutomatedRepository.delete(lightAutomated);
                                return LightSettingResponse.builder()
                                                .lightSetting(lightAutomated)
                                                .message("Delete light setting successfully!")
                                                .mode(mode)
                                                .build();

                        case "manual":
                                LightManualRepository lightManualRepository = lightSettingFactory
                                                .getLightSettingRepository(LightManualRepository.class);
                                LightManual lightManual = lightManualRepository.findById(id)
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                                "Light Setting with id " + id + "doesn't exist!"));
                                lightManualRepository.delete(lightManual);
                                return LightSettingResponse.builder()
                                                .lightSetting(lightManual)
                                                .message("Delete light setting successfully!")
                                                .mode(mode)
                                                .build();
                        case "scheduled":
                                LightScheduledRepository lightScheduledRepository = lightSettingFactory
                                                .getLightSettingRepository(LightScheduledRepository.class);
                                LightScheduled lightScheduled = lightScheduledRepository.findById(id)
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                                "Light Setting with id " + id + "doesn't exist!"));
                                lightScheduledRepository.delete(lightScheduled);
                                return LightSettingResponse.builder()
                                                .lightSetting(lightScheduled)
                                                .message("Delete light setting successfully!")
                                                .mode(mode)
                                                .build();
                        default:
                                throw new IllegalArgumentException("Invalid light setting mode: " + mode);
                }
        }
}