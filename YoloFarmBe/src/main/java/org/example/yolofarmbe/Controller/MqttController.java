package org.example.yolofarmbe.Controller;

import java.util.List;

import org.example.yolofarmbe.Response.TemperatureMqtt;
import org.example.yolofarmbe.Service.MqttService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/mqtt")
public class MqttController {

   private final MqttService mqttService;

   public MqttController(MqttService mqttService) {
      this.mqttService = mqttService;
   }

   @GetMapping("/temperature")
   public List<String> getAllTemperatureDataMqtt() {
      return mqttService.getAllTemperatureData("temperature");
   }

   @GetMapping("/moisture")
   public List<String> getAllMoistureDataMqtt() {
      return mqttService.getAllTemperatureData("moisture");
   }

   @GetMapping("/humidity")
   public List<String> getAllHumidityDataMqtt() {
      return mqttService.getAllTemperatureData("humidity");
   }

   @GetMapping("/light")
   public List<String> getAllLightDataMqtt() {
      return mqttService.getAllTemperatureData("light");
   }

   @PostMapping("/water")
   public void waterOn() {
      mqttService.publishMessage("water", "1");
   }

}
