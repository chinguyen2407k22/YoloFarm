package org.example.yolofarmbe.Service;

import org.example.yolofarmbe.Entity.*;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Factory.TemperatureSettingFactory;
import org.example.yolofarmbe.Repository.*;
import org.example.yolofarmbe.Request.TemperatureSettingRequest;
import org.example.yolofarmbe.Response.TemperatureSettingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemperatureSettingService {
        @Autowired
        private TemperatureSettingFactory temperatureSettingFactory;

        @Autowired
        private MqttService mqttService;

        @Autowired
        private FarmRepository farmRepository;

        public List<? extends TemperatureSetting> getAllTemperatureSetting(String mode) {
                switch (mode.toLowerCase()) {
                        case "automated":
                                TemperatureAutomatedRepository temperatureAutomatedRepository = temperatureSettingFactory
                                                .getTempSettingRepository(TemperatureAutomatedRepository.class);
                                return temperatureAutomatedRepository.findAll();
                        case "manual":
                                TemperatureManualRepository temperatureManualRepository = temperatureSettingFactory
                                                .getTempSettingRepository(TemperatureManualRepository.class);
                                return temperatureManualRepository.findAll();
                        case "scheduled":
                                TemperatureScheduledRepository temperatureScheduledRepository = temperatureSettingFactory
                                                .getTempSettingRepository(TemperatureScheduledRepository.class);
                                return temperatureScheduledRepository.findAll();
                        default:
                                throw new IllegalArgumentException("Invalid temperature setting mode: " + mode);
                }
        }

        public TemperatureSettingResponse<? extends TemperatureSetting> getTemperatureSettingById(String mode, int id) {
                switch (mode.toLowerCase()) {
                        case "automated":
                                TemperatureAutomatedRepository temperatureAutomatedRepository = temperatureSettingFactory
                                                .getTempSettingRepository(TemperatureAutomatedRepository.class);
                                TemperatureAutomated temperatureAutomated = temperatureAutomatedRepository.findById(id)
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                                "Temperature Setting with id " + id
                                                                                + "doesn't exist!"));
                                return TemperatureSettingResponse.builder()
                                                .temperatureSetting(temperatureAutomated)
                                                .mode(mode)
                                                .message("Get temperature setting successfully")
                                                .build();
                        case "manual":
                                TemperatureManualRepository temperatureManualRepository = temperatureSettingFactory
                                                .getTempSettingRepository(TemperatureManualRepository.class);
                                TemperatureManual temperatureManual = temperatureManualRepository.findById(id)
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                                "Temperature Setting with id " + id
                                                                                + "doesn't exist!"));
                                return TemperatureSettingResponse.builder()
                                                .temperatureSetting(temperatureManual)
                                                .mode(mode)
                                                .message("Get temperature setting successfully")
                                                .build();
                        case "scheduled":
                                TemperatureScheduledRepository temperatureScheduledRepository = temperatureSettingFactory
                                                .getTempSettingRepository(TemperatureScheduledRepository.class);
                                TemperatureScheduled temperatureScheduled = temperatureScheduledRepository.findById(id)
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                                "Temperature Setting with id " + id
                                                                                + "doesn't exist!"));
                                return TemperatureSettingResponse.builder()
                                                .temperatureSetting(temperatureScheduled)
                                                .mode(mode)
                                                .message("Get temperature setting successfully")
                                                .build();
                        default:
                                throw new IllegalArgumentException("Invalid temperature setting mode: " + mode);
                }
        }

        public TemperatureSettingResponse<? extends TemperatureSetting> getTemperatureSettingByFarm(String mode,
                        int farm_id) {
                Farm farm = farmRepository.findById(farm_id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Farm with id " + farm_id + "doesn't exist!"));
                switch (mode.toLowerCase()) {
                        case "automated":
                                TemperatureAutomatedRepository temperatureAutomatedRepository = temperatureSettingFactory
                                                .getTempSettingRepository(TemperatureAutomatedRepository.class);
                                TemperatureAutomated temperatureAutomated = temperatureAutomatedRepository
                                                .findByFarm_Id(farm_id);
                                return TemperatureSettingResponse.builder()
                                                .temperatureSetting(temperatureAutomated)
                                                .mode(mode)
                                                .message("Get temperature setting successfully")
                                                .build();
                        case "manual":
                                TemperatureManualRepository temperatureManualRepository = temperatureSettingFactory
                                                .getTempSettingRepository(TemperatureManualRepository.class);
                                TemperatureManual temperatureManual = temperatureManualRepository
                                                .findByFarm_Id(farm_id);
                                return TemperatureSettingResponse.builder()
                                                .temperatureSetting(temperatureManual)
                                                .mode(mode)
                                                .message("Get temperature setting successfully")
                                                .build();
                        case "scheduled":
                                TemperatureScheduledRepository temperatureScheduledRepository = temperatureSettingFactory
                                                .getTempSettingRepository(TemperatureScheduledRepository.class);
                                TemperatureScheduled temperatureScheduled = temperatureScheduledRepository
                                                .findByFarm_Id(farm_id);
                                return TemperatureSettingResponse.builder()
                                                .temperatureSetting(temperatureScheduled)
                                                .mode(mode)
                                                .message("Get temperature setting successfully")
                                                .build();
                        default:
                                throw new IllegalArgumentException("Invalid temperature setting mode: " + mode);
                }
        }

        public TemperatureSettingResponse<? extends TemperatureSetting> addTemperatureSetting(String mode,
                        TemperatureSettingRequest request) {

                switch (mode.toLowerCase()) {
                        case "automated":
                                TemperatureAutomatedRepository temperatureAutomatedRepository = temperatureSettingFactory
                                                .getTempSettingRepository(TemperatureAutomatedRepository.class);
                                TemperatureAutomated temperatureAutomated = new TemperatureAutomated();
                                if (request.getSendWarning() != null) {
                                        temperatureAutomated.setSendWarning(request.getSendWarning());
                                }
                                if (request.getFarm() != null) {
                                        int farm_id = request.getFarm().getId();
                                        Farm farm = farmRepository.findById(farm_id)
                                                        .orElseThrow(
                                                                        () -> new ResourceNotFoundException(
                                                                                        "Farm with id " + farm_id
                                                                                                        + "doesn't exist!"));
                                        temperatureAutomated.setFarm(farm);
                                }
                                if (request.getUpper() > 0) {
                                        temperatureAutomated.setUpper(request.getUpper());
                                }
                                if (request.getLower() > 0) {
                                        temperatureAutomated.setLower(request.getLower());
                                }
                                temperatureAutomatedRepository.save(temperatureAutomated);
                                return TemperatureSettingResponse.builder()
                                                .temperatureSetting(temperatureAutomated)
                                                .mode(mode)
                                                .message("Add temperature setting successfully")
                                                .build();
                        case "manual":
                                TemperatureManualRepository temperatureManualRepository = temperatureSettingFactory
                                                .getTempSettingRepository(TemperatureManualRepository.class);
                                TemperatureManual temperatureManual = new TemperatureManual();
                                if (request.getFarm() != null) {
                                        int farm_id = request.getFarm().getId();
                                        Farm farm = farmRepository.findById(farm_id)
                                                        .orElseThrow(
                                                                        () -> new ResourceNotFoundException(
                                                                                        "Farm with id " + farm_id
                                                                                                        + "doesn't exist!"));
                                        temperatureManual.setFarm(farm);
                                }
                                if (request.getSunShade() != null) {
                                        temperatureManual.setSunShade(request.getSunShade());
                                        if (request.getSunShade() == State.ON) {
                                                mqttService.publishMessage("water", "1");
                                        } else {
                                                mqttService.publishMessage("water", "0");

                                        }
                                }
                                temperatureManualRepository.save(temperatureManual);
                                return TemperatureSettingResponse.builder()
                                                .temperatureSetting(temperatureManual)
                                                .mode(mode)
                                                .message("Add temperature setting successfully")
                                                .build();
                        case "scheduled":
                                TemperatureScheduledRepository temperatureScheduledRepository = temperatureSettingFactory
                                                .getTempSettingRepository(TemperatureScheduledRepository.class);
                                TemperatureScheduled temperatureScheduled = new TemperatureScheduled();
                                if (request.getFarm() != null) {
                                        int farm_id = request.getFarm().getId();
                                        Farm farm = farmRepository.findById(farm_id)
                                                        .orElseThrow(
                                                                        () -> new ResourceNotFoundException(
                                                                                        "Farm with id " + farm_id
                                                                                                        + "doesn't exist!"));
                                        temperatureScheduled.setFarm(farm);
                                }
                                if (request.getSendWarning() != null) {
                                        temperatureScheduled.setSendWarning(request.getSendWarning());
                                }
                                temperatureScheduledRepository.save(temperatureScheduled);
                                return TemperatureSettingResponse.builder()
                                                .temperatureSetting(temperatureScheduled)
                                                .mode(mode)
                                                .message("Add temperature setting successfully")
                                                .build();
                        default:
                                throw new IllegalArgumentException("Invalid temperature setting mode: " + mode);
                }
        }

        public TemperatureSettingResponse<? extends TemperatureSetting> updateTemperatureSetting(String mode, int id,
                        TemperatureSettingRequest request) {

                switch (mode.toLowerCase()) {
                        case "automated":
                                TemperatureAutomatedRepository temperatureAutomatedRepository = temperatureSettingFactory
                                                .getTempSettingRepository(TemperatureAutomatedRepository.class);
                                TemperatureAutomated temperatureAutomated = temperatureAutomatedRepository.findById(id)
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                                "Temperature Setting with id " + id
                                                                                + "doesn't exist!"));
                                if (request.getSendWarning() != null) {
                                        temperatureAutomated.setSendWarning(request.getSendWarning());
                                }
                                if (request.getFarm() != null) {
                                        int farm_id = request.getFarm().getId();
                                        Farm farm = farmRepository.findById(farm_id)
                                                        .orElseThrow(
                                                                        () -> new ResourceNotFoundException(
                                                                                        "Farm with id " + farm_id
                                                                                                        + "doesn't exist!"));
                                        temperatureAutomated.setFarm(farm);
                                }
                                if (request.getUpper() > 0) {
                                        temperatureAutomated.setUpper(request.getUpper());
                                }
                                if (request.getLower() > 0) {
                                        temperatureAutomated.setLower(request.getLower());
                                }
                                temperatureAutomatedRepository.save(temperatureAutomated);
                                return TemperatureSettingResponse.builder()
                                                .temperatureSetting(temperatureAutomated)
                                                .mode(mode)
                                                .message("Update temperature setting successfully")
                                                .build();
                        case "manual":
                                TemperatureManualRepository temperatureManualRepository = temperatureSettingFactory
                                                .getTempSettingRepository(TemperatureManualRepository.class);
                                TemperatureManual temperatureManual = new TemperatureManual();
                                if (request.getFarm() != null) {
                                        int farm_id = request.getFarm().getId();
                                        Farm farm = farmRepository.findById(farm_id)
                                                        .orElseThrow(
                                                                        () -> new ResourceNotFoundException(
                                                                                        "Farm with id " + farm_id
                                                                                                        + "doesn't exist!"));
                                        temperatureManual.setFarm(farm);
                                }
                                if (request.getSunShade() != null) {
                                        temperatureManual.setSunShade(request.getSunShade());
                                }
                                temperatureManualRepository.save(temperatureManual);
                                return TemperatureSettingResponse.builder()
                                                .temperatureSetting(temperatureManual)
                                                .mode(mode)
                                                .message("Update temperature setting successfully")
                                                .build();
                        case "scheduled":
                                TemperatureScheduledRepository temperatureScheduledRepository = temperatureSettingFactory
                                                .getTempSettingRepository(TemperatureScheduledRepository.class);
                                TemperatureScheduled temperatureScheduled = temperatureScheduledRepository.findById(id)
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                                "Temperature Setting with id " + id
                                                                                + "doesn't exist!"));
                                if (request.getFarm() != null) {
                                        int farm_id = request.getFarm().getId();
                                        Farm farm = farmRepository.findById(farm_id)
                                                        .orElseThrow(
                                                                        () -> new ResourceNotFoundException(
                                                                                        "Farm with id " + farm_id
                                                                                                        + "doesn't exist!"));
                                        temperatureScheduled.setFarm(farm);
                                }
                                if (request.getSendWarning() != null) {
                                        temperatureScheduled.setSendWarning(request.getSendWarning());
                                }
                                temperatureScheduledRepository.save(temperatureScheduled);
                                return TemperatureSettingResponse.builder()
                                                .temperatureSetting(temperatureScheduled)
                                                .mode(mode)
                                                .message("Update temperature setting successfully")
                                                .build();
                        default:
                                throw new IllegalArgumentException("Invalid temperature setting mode: " + mode);
                }
        }

        public TemperatureSettingResponse<? extends TemperatureSetting> deleteTemperatureSetting(String mode, int id) {
                switch (mode.toLowerCase()) {
                        case "automated":
                                TemperatureAutomatedRepository temperatureAutomatedRepository = temperatureSettingFactory
                                                .getTempSettingRepository(TemperatureAutomatedRepository.class);
                                TemperatureAutomated temperatureAutomated = temperatureAutomatedRepository.findById(id)
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                                "Temperature Setting with id " + id
                                                                                + "doesn't exist!"));
                                temperatureAutomatedRepository.delete(temperatureAutomated);
                                return TemperatureSettingResponse.builder()
                                                .temperatureSetting(temperatureAutomated)
                                                .mode(mode)
                                                .message("Delete temperature setting successfully")
                                                .build();
                        case "manual":
                                TemperatureManualRepository temperatureManualRepository = temperatureSettingFactory
                                                .getTempSettingRepository(TemperatureManualRepository.class);
                                TemperatureManual temperatureManual = temperatureManualRepository.findById(id)
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                                "Temperature Setting with id " + id
                                                                                + "doesn't exist!"));
                                temperatureManualRepository.delete(temperatureManual);
                                return TemperatureSettingResponse.builder()
                                                .temperatureSetting(temperatureManual)
                                                .mode(mode)
                                                .message("Delete temperature setting successfully")
                                                .build();
                        case "scheduled":
                                TemperatureScheduledRepository temperatureScheduledRepository = temperatureSettingFactory
                                                .getTempSettingRepository(TemperatureScheduledRepository.class);
                                TemperatureScheduled temperatureScheduled = temperatureScheduledRepository.findById(id)
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                                "Temperature Setting with id " + id
                                                                                + "doesn't exist!"));
                                temperatureScheduledRepository.delete(temperatureScheduled);
                                return TemperatureSettingResponse.builder()
                                                .temperatureSetting(temperatureScheduled)
                                                .mode(mode)
                                                .message("Delete temperature setting successfully")
                                                .build();
                        default:
                                throw new IllegalArgumentException("Invalid temperature setting mode: " + mode);
                }
        }

}
