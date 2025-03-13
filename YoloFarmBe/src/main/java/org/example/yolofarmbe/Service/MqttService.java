package org.example.yolofarmbe.Service;

import org.springframework.http.HttpHeaders;

import java.util.Arrays;

import org.eclipse.paho.client.mqttv3.*;
import org.example.yolofarmbe.Entity.HumidityRecord;
import org.example.yolofarmbe.Entity.MoistureRecord;
import org.example.yolofarmbe.Entity.TemperatureRecord;
import org.example.yolofarmbe.Response.AdafruitResponse;
import org.example.yolofarmbe.Response.TemperatureResponse;

import java.util.List;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MqttService {

   private final String BROKER_URL = "tcp://io.adafruit.com:1883"; // Adafruit MQTT broker
   private final String USERNAME = "phuctrangeo1709";
   private final String AIO_KEY = "aio_voqw706gj4OynKPHPnLKY7bz5Lqh";
   private final String FEED_TEMPERATURE = "temperature";
   private final String FEED_MOISTURE = "moisture";
   private final String FEED_HUMIDITY = "humidity";
   private final String FEED_ATOGGLE = "a-toggle";
   private final String FEED_MTOGGLE = "m-toggle";

   private MqttClient client;

   private RecordService recordService;

   private final RestTemplate restTemplate = new RestTemplate();

   private final TemperatureRecord temperatureRecord = new TemperatureRecord();

   private final HumidityRecord humidityRecord = new HumidityRecord();

   private final MoistureRecord moistureRecord = new MoistureRecord();

   @Autowired
   public MqttService(RecordService recordService) {
      try {
         this.recordService = recordService;
         client = new MqttClient(BROKER_URL, MqttClient.generateClientId());
         MqttConnectOptions options = new MqttConnectOptions();
         options.setUserName(USERNAME);
         options.setPassword(AIO_KEY.toCharArray());
         client.connect(options);

         // Đăng ký lắng nghe dữ liệu từ feed
         client.subscribe(USERNAME + "/feeds/+", (topic, msg) -> {
            System.out.println("Received from " + topic + ": " + new String(msg.getPayload()));
         });

      } catch (MqttException e) {
         e.printStackTrace();
      }
   }

   @Scheduled(fixedRate = 3000) // Gọi API mỗi 5 giây
   public void fetchDataFromAdafruit() {
      try {
         HttpHeaders headers = new HttpHeaders();
         headers.set("X-AIO-Key", AIO_KEY);

         HttpEntity<String> entity = new HttpEntity<>(headers);

         List<String> feeds = Arrays.asList(FEED_TEMPERATURE, FEED_MOISTURE, FEED_HUMIDITY, FEED_ATOGGLE, FEED_MTOGGLE);

         for (String feed : feeds) {
            String URL = "https://io.adafruit.com/api/v2/" + USERNAME + "/feeds/" + feed + "/data/last";
            ResponseEntity<AdafruitResponse> response = restTemplate.exchange(URL, HttpMethod.GET, entity,
                  AdafruitResponse.class);

            if (feed.equals(FEED_TEMPERATURE)) {
               temperatureRecord.setRecordValue(Double.parseDouble(response.getBody().getValue()));
               recordService.SaveRecords(feed, temperatureRecord);
            } else if (feed.equals(FEED_MOISTURE)) {
               moistureRecord.setRecordValue(Double.parseDouble(response.getBody().getValue()));
               recordService.SaveRecords(feed, moistureRecord);
            } else if (feed.equals(FEED_HUMIDITY)) {
               humidityRecord.setRecordValue(Double.parseDouble(response.getBody().getValue()));
               recordService.SaveRecords(feed, humidityRecord);
            }

            System.out.println("Fetched data from " + feed + ": " + response.getBody().getValue());
         }
         System.out.println("\n----------------------------------------------------------------------");

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void publishMessage(String FEED_NAME, String message) {
      try {
         MqttMessage mqttMessage = new MqttMessage(message.getBytes());
         mqttMessage.setQos(1);
         client.publish(USERNAME + "/feeds/" + FEED_NAME, mqttMessage);
         System.out.println("Published message: " + message);
      } catch (MqttException e) {
         e.printStackTrace();
      }
   }
}
