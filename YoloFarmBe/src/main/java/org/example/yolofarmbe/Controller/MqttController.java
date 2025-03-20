package org.example.yolofarmbe.Controller;

import org.example.yolofarmbe.Service.MqttService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mqtt")
public class MqttController {

   private final MqttService mqttService;

   public MqttController(MqttService mqttService) {
      this.mqttService = mqttService;
   }

   @PostMapping("/temperature")
   public String SendTemperature(@RequestParam String value) {
      mqttService.publishMessage("temperature", value);
      return "Message sent: " + value;
   }

   @PostMapping("/moisture")
   public String SendMoisture(@RequestParam String value) {
      mqttService.publishMessage("moisture", value);
      return "Message sent: " + value;
   }

   @PostMapping("/humidity")
   public String SendHumidity(@RequestParam String value) {
      mqttService.publishMessage("humidity", value);
      return "Message sent: " + value;
   }

   @PostMapping("/a-toggle")
   public String SendaToggle(@RequestParam String value) {
      mqttService.publishMessage("a-toggle", value);
      return "Message sent: " + value;
   }

   @PostMapping("/m-toggle")
   public String SendmToggle(@RequestParam String value) {
      mqttService.publishMessage("m-toggle", value);
      return "Message sent: " + value;
   }

}
