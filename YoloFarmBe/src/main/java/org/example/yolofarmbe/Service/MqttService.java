package org.example.yolofarmbe.Service;

import org.springframework.http.HttpHeaders;

import java.time.Instant;
import java.util.Arrays;

import org.eclipse.paho.client.mqttv3.*;
import org.example.yolofarmbe.Entity.AmountOfWaterRecord;
import org.example.yolofarmbe.Entity.Farm;
import org.example.yolofarmbe.Entity.Record;
import org.example.yolofarmbe.Entity.HumidityRecord;
import org.example.yolofarmbe.Entity.LightRecord;
import org.example.yolofarmbe.Entity.MoistureRecord;
import org.example.yolofarmbe.Entity.TemperatureRecord;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Repository.FarmRepository;
import org.example.yolofarmbe.Repository.RecordRepository;
import org.example.yolofarmbe.Response.AdafruitResponse;
import org.example.yolofarmbe.Response.TemperatureMqtt;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MqttService {

   private final String BROKER_URL = "tcp://io.adafruit.com:1883";
   private final String USERNAME = "fungchill";
   private final String AIO_KEY = "aio_qhOp85Q5RZjfaYcpSMyizOk0gTDp";

   private final String FEED_TEMPERATURE = "temperature";
   private final String FEED_MOISTURE = "moisture";
   private final String FEED_HUMIDITY = "humidity";
   private final String FEED_LIGHT = "light";
   private final String FEED_WATER = "water";
   private final String FEED_FAN = "fan";

   private MqttClient client;

   private RecordService recordService;

   private RestTemplate restTemplate = new RestTemplate();

   private FarmRepository farmRepository;

   public MqttService(RecordService recordService, FarmRepository farmRepository) {
      try {
         this.recordService = recordService;
         this.farmRepository = farmRepository;
         client = new MqttClient(BROKER_URL, MqttClient.generateClientId());
         MqttConnectOptions options = new MqttConnectOptions();
         options.setUserName(USERNAME);
         options.setPassword(AIO_KEY.toCharArray());
         client.connect(options);

         client.subscribe(USERNAME + "/feeds/+");

      } catch (MqttException e) {
         e.printStackTrace();
      }
   }

   @Scheduled(fixedRate = 30 * 1000)
   public void fetchDataFromAdafruit() {
      try {
         HttpHeaders headers = new HttpHeaders();
         headers.set("X-AIO-Key", AIO_KEY);

         HttpEntity<String> entity = new HttpEntity<>(headers);

         List<String> feeds = Arrays.asList(FEED_MOISTURE, FEED_WATER, FEED_FAN, FEED_LIGHT, FEED_HUMIDITY,
               FEED_TEMPERATURE);

         for (String feed : feeds) {

            // if (feed.equals(FEED_WATER)) {
            // String URL = "https://io.adafruit.com/api/v2/" + USERNAME + "/feeds/" + feed
            // + "/data?limit=1";
            // ResponseEntity<List<AdafruitResponse>> response = restTemplate.exchange(URL,
            // HttpMethod.GET, entity,
            // new ParameterizedTypeReference<List<AdafruitResponse>>() {
            // });

            // Integer farm_id = 1;
            // Farm farm = farmRepository.findById(farm_id)
            // .orElseThrow(() -> new ResourceNotFoundException("Farm with id " + farm_id +
            // "not exist"));

            // AmountOfWaterRecord amountofwaterRecord = new AmountOfWaterRecord();
            // amountofwaterRecord.setRecordValue(Double.parseDouble(response.getBody().get(0).getValue()));
            // amountofwaterRecord.setRecordTime(Instant.now());
            // amountofwaterRecord.setFarm(farm);
            // // recordService.SaveRecords(feed, amountofwaterRecord);

            // System.out.println("Fetched data from " + feed + ": " +
            // response.getBody().get(0).getValue());
            // } else
            {
               String URL = "https://io.adafruit.com/api/v2/" + USERNAME + "/feeds/" + feed
                     + "/data/last";
               ResponseEntity<AdafruitResponse> response = restTemplate.exchange(URL,
                     HttpMethod.GET, entity,
                     AdafruitResponse.class);

               Integer farm_id = 1;
               Farm farm = farmRepository.findById(farm_id)
                     .orElseThrow(() -> new ResourceNotFoundException("Farm with id " + farm_id +
                           "not exist"));

               if (feed.equals(FEED_TEMPERATURE)) {
                  TemperatureRecord temperatureRecord = new TemperatureRecord();
                  temperatureRecord.setRecordValue(Double.parseDouble(response.getBody().getValue()));
                  temperatureRecord.setRecordTime(Instant.now());
                  temperatureRecord.setFarm(farm);
                  recordService.SaveRecords(feed, temperatureRecord);
               } else if (feed.equals(FEED_MOISTURE)) {
                  MoistureRecord moistureRecord = new MoistureRecord();
                  moistureRecord.setRecordValue(Double.parseDouble(response.getBody().getValue()));
                  moistureRecord.setRecordTime(Instant.now());
                  moistureRecord.setFarm(farm);
                  recordService.SaveRecords(feed, moistureRecord);
               } else if (feed.equals(FEED_HUMIDITY)) {
                  HumidityRecord humidityRecord = new HumidityRecord();
                  humidityRecord.setRecordValue(Double.parseDouble(response.getBody().getValue()));
                  humidityRecord.setRecordTime(Instant.now());
                  humidityRecord.setFarm(farm);
                  recordService.SaveRecords(feed, humidityRecord);
               } else if (feed.equals(FEED_LIGHT)) {
                  LightRecord lightRecord = new LightRecord();
                  lightRecord.setRecordValue(Double.parseDouble(response.getBody().getValue()));
                  lightRecord.setRecordTime(Instant.now());
                  lightRecord.setFarm(farm);
                  recordService.SaveRecords(feed, lightRecord);
               }

               System.out.println("Fetched data from " + feed + ": " +
                     response.getBody().getValue());
            }
         }
         System.out.println("\n----------------------------------------------------------------------");

         // Random random = new Random();
         // int btn = random.nextInt(4);
         // if (btn % 2 == 0) {
         // publishMessage(FEED_FAN, "0");
         // publishMessage(FEED_WATER, "0");
         // } else {
         // publishMessage(FEED_FAN, "1");
         // publishMessage(FEED_WATER, "1");
         // }
         // publishMessage(FEED_TEMPERATURE, String.valueOf(random.nextInt(60)));
         // publishMessage(FEED_MOISTURE, String.valueOf(random.nextInt(100)));
         // publishMessage(FEED_HUMIDITY, String.valueOf(random.nextInt(60)));
         // publishMessage(FEED_LIGHT, String.valueOf(random.nextInt(5000)));

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void publishMessage(String FEED_NAME, String message) {
      try {
         MqttMessage mqttMessage = new MqttMessage(message.getBytes());
         mqttMessage.setQos(1);
         client.publish(USERNAME + "/feeds/" + FEED_NAME, mqttMessage);
         System.out.println("Published to Feed:" + FEED_NAME + " - message:" + message);
      } catch (MqttException e) {
         e.printStackTrace();
      }
   }

   public List<String> getAllTemperatureData(String FEED_NAME) {
      HttpHeaders headers = new HttpHeaders();
      headers.set("X-AIO-Key", AIO_KEY);

      HttpEntity<String> entity = new HttpEntity<>(headers);

      String url = "https://io.adafruit.com/api/v2/" + USERNAME + "/feeds/" + FEED_NAME + "/data";

      ResponseEntity<TemperatureMqtt[]> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            TemperatureMqtt[].class);

      if (response.getBody() == null) {
         return List.of();
      }

      return Arrays.stream(response.getBody())
            .map(TemperatureMqtt::getValue)
            .collect(Collectors.toList());
   }
}
