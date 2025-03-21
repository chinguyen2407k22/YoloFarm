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

import java.util.List;
import java.util.Random;

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
   private final String USERNAME = "phuctrangeo1709";
   private final String AIO_KEY = "aio_voqw706gj4OynKPHPnLKY7bz5Lqh";

   private final String FEED_TEMPERATURE = "temperature";
   private final String FEED_MOISTURE = "moisture";
   private final String FEED_HUMIDITY = "humidity";
   private final String FEED_LIGHT = "light";
   private final String FEED_AMOUNTOFWATER = "amountwater";
   private final String FEED_ATOGGLE = "a-toggle";
   private final String FEED_MTOGGLE = "m-toggle";

   private MqttClient client;

   private RecordService recordService;

   private RestTemplate restTemplate = new RestTemplate();

   private FarmRepository farmRepository;

   @Autowired
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

   @Scheduled(fixedRate = 30 * 1000) // Gọi API mỗi 5 giây
   public void fetchDataFromAdafruit() {
      try {
         HttpHeaders headers = new HttpHeaders();
         headers.set("X-AIO-Key", AIO_KEY);

         HttpEntity<String> entity = new HttpEntity<>(headers);

         List<String> feeds = Arrays.asList(FEED_TEMPERATURE, FEED_MOISTURE, FEED_HUMIDITY, FEED_ATOGGLE, FEED_MTOGGLE,
               FEED_AMOUNTOFWATER);

         for (String feed : feeds) {

            if (feed.equals(FEED_AMOUNTOFWATER)) {
               String URL = "https://io.adafruit.com/api/v2/" + USERNAME + "/feeds/" + feed
                     + "/data?limit=1";
               ResponseEntity<List<AdafruitResponse>> response = restTemplate.exchange(URL,
                     HttpMethod.GET, entity,
                     new ParameterizedTypeReference<List<AdafruitResponse>>() {
                     });

               Integer farm_id = 1;
               Farm farm = farmRepository.findById(farm_id)
                     .orElseThrow(() -> new ResourceNotFoundException("Farm with id " + farm_id +
                           "not exist"));

               AmountOfWaterRecord amountofwaterRecord = new AmountOfWaterRecord();
               amountofwaterRecord.setRecordValue(Double.parseDouble(response.getBody().get(0).getValue()));
               amountofwaterRecord.setRecordTime(Instant.now());
               amountofwaterRecord.setFarm(farm);
               recordService.SaveRecords(feed, amountofwaterRecord);

               System.out.println("Fetched data from " + feed + ": " +
                     response.getBody().get(0).getValue());
            } else {
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
         // int x = random.nextInt(10);
         // publishMessage(FEED_TEMPERATURE, String.valueOf(random.nextInt(60)));
         // publishMessage(FEED_MOISTURE, String.valueOf(random.nextInt(60)));
         // publishMessage(FEED_HUMIDITY, String.valueOf(random.nextInt(60)));
         // publishMessage(FEED_AMOUNTOFWATER, String.valueOf(random.nextDouble(1)));
         // if (x % 2 == 0) {
         // publishMessage(FEED_ATOGGLE, "AW-0");
         // publishMessage(FEED_MTOGGLE, "MW-0");
         // x++;
         // } else {
         // publishMessage(FEED_ATOGGLE, "AW-1");
         // publishMessage(FEED_MTOGGLE, "MW-1");
         // x++;
         // }

         // Farm farm = new Farm();
         // farm.setCrop("tonton");
         // farm.setFarmName("tonton");
         // farm.setFarmSize(100.0);
         // farm.setId(1);
         // farmService.addNewFarm(farm);

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
