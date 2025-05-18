package org.example.yolofarmbe.Controller;

import java.util.List;

import org.example.yolofarmbe.Response.RangeValueMqttResponse;
import org.example.yolofarmbe.Service.MqttService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/mqtt")
public class MqttController {

   private final MqttService mqttService;

   public MqttController(MqttService mqttService) {
      this.mqttService = mqttService;
   }

   @GetMapping("/{feed}")
   public List<Double> getAllDataMqtt(@PathVariable String feed) {
      return mqttService.getAllDataFeed(feed);
   }

   @GetMapping("/{feed}/range")
   public ResponseEntity<RangeValueMqttResponse> getRangeValue(@PathVariable String feed) {
      RangeValueMqttResponse rangeValueMqtt = mqttService.getRangeValue(feed);
      return ResponseEntity.ok(rangeValueMqtt);
   }

   @PostMapping("/{device}/{val}")
   public ResponseEntity<String> fan(@PathVariable String val, @PathVariable String device) {
      try {
         mqttService.publishMessage(device, val);
         return ResponseEntity.ok("Update fan device successfully");
      } catch (Exception e) {
         System.out.println("Error updating " + device + " device");
         throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating " + device + " device", e);
      }
   }

   @PutMapping("/{feed}")
   public ResponseEntity<String> UpdateRangeValue(@PathVariable String feed, @RequestParam int minValue,
         @RequestParam int maxValue) {
      try {
         mqttService.UpdateRangeValue(feed, minValue, maxValue);
         return ResponseEntity.ok("Updated range for feed: " + feed);
      } catch (Exception e) {
         System.out.println("Error updating range: " + e.getMessage());
         throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update range", e);
      }
   }

   @GetMapping("{feed}/auto/on")
   public void TurnOnAuto(@PathVariable String feed, @RequestParam long duration) {
      // mqttService.TurnOnDeviceAuto(feed, "daily", null, duration);
   }

   // @GetMapping("{feed}/auto/off")
   // public void TurnOffAuto(@PathVariable String feed) {
   //    mqttService.TurnOffDeviceAuto(feed);
   // }

}
