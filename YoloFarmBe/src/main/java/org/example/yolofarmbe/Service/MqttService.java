package org.example.yolofarmbe.Service;

import org.springframework.http.HttpHeaders;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.eclipse.paho.client.mqttv3.*;
import org.example.yolofarmbe.Entity.AmountOfWaterRecord;
import org.example.yolofarmbe.Entity.DailyTask;
import org.example.yolofarmbe.Entity.Farm;
import org.example.yolofarmbe.Entity.Record;
import org.example.yolofarmbe.Entity.HumidityRecord;
import org.example.yolofarmbe.Entity.LightRecord;
import org.example.yolofarmbe.Entity.LightScheduled;
import org.example.yolofarmbe.Entity.MoistureRecord;
import org.example.yolofarmbe.Entity.TemperatureRecord;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Repository.DailyTaskRepository;
import org.example.yolofarmbe.Repository.FarmRepository;
import org.example.yolofarmbe.Repository.RecordRepository;
import org.example.yolofarmbe.Request.SchedulerRequest;
import org.example.yolofarmbe.Response.AdafruitResponse;
import org.example.yolofarmbe.Response.FeedMqttResponse;
import org.example.yolofarmbe.Response.RangeValueMqttResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class MqttService {

   private final String BROKER_URL = "tcp://io.adafruit.com:1883";
   private final String USERNAME = "fungchill";
   private final String AIO_KEY = System.getenv("ADAFRUIT_IO_KEY");
   private final String DASHBOARD_KEY = "yolo-farm";
   private final String FEED_TEMPERATURE = "temperature";
   private final String FEED_MOISTURE = "moisture";
   private final String FEED_HUMIDITY = "humidity";
   private final String FEED_LIGHT = "light";
   private final String FEED_WATER = "water";
   private final String FEED_FAN = "fan";
   private volatile boolean waterRun = true;
   private volatile boolean fanRun = true;

   private MqttClient client;

   private MqttConnectOptions options;

   @Autowired
   private RecordService recordService;

   @Autowired
   private RestTemplate restTemplate;

   @Autowired
   private FarmRepository farmRepository;

   @Autowired
   private TaskScheduler taskScheduler;

   private final Map<String, ScheduledFuture<?>> tasks = new ConcurrentHashMap<>();

   public MqttService() {
      try {
         client = new MqttClient(BROKER_URL, MqttClient.generateClientId());
         options = new MqttConnectOptions();
         options.setUserName(USERNAME);
         options.setPassword(AIO_KEY.toCharArray());
         options.setAutomaticReconnect(true);
         client.connect(options);
         while (!client.isConnected())
            client.connect(options);
         client.subscribe(USERNAME + "/feeds/+");

      } catch (MqttException e) {
         e.printStackTrace();
      }
   }

   @Scheduled(fixedRate = 20 * 1000)
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

            // System.out.println("Fetched data from " + fee d + ": " +
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

         Random random = new Random();
         publishMessage(FEED_TEMPERATURE, String.valueOf(random.nextInt(60)));
         publishMessage(FEED_MOISTURE, String.valueOf(random.nextInt(100)));
         publishMessage(FEED_HUMIDITY, String.valueOf(random.nextInt(60)));
         publishMessage(FEED_LIGHT, String.valueOf(random.nextInt(700, 750)));

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

   // ------------------------------------------------------------------------------------

   @Async
   public void TaskRepeat(String FEED_NAME, long duration, String cronExpression) {
      System.out.println("Turn on device automation");

      Runnable task = () -> {
         System.out.println("Start " + FEED_NAME + " device");
         publishMessage(FEED_NAME, "1");
         long endTime = System.currentTimeMillis() + duration;
         int i = 0;
         while (System.currentTimeMillis() < endTime) {
            System.out.println("Task run" + FEED_NAME + ": " + ++i);
            try {
               Thread.sleep(1000); // Thực hiện mỗi giây
            } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
            }
         }
         publishMessage(FEED_NAME, "0");
         System.out.println("Start " + FEED_NAME + " device");

      };

      CronTrigger cronTrigger = new CronTrigger(cronExpression);
      ScheduledFuture<?> future = taskScheduler.schedule(task, cronTrigger);
      tasks.put(FEED_NAME, future); // first exec
   }

   // @Async
   public void TurnOnDeviceSchedule(String FEED_NAME, String schedulerType, SchedulerRequest schedulerRequest) {
      long duration = schedulerRequest.getDuration();
      // long duration = 5000;
      switch (schedulerType.toLowerCase()) {
         case "daily":

            String cronExpression = "* * * * * *";
            TaskRepeat(FEED_NAME, duration, cronExpression);

            // LocalTime time = schedulerRequest.getTime();
            // String hour = String.valueOf(time.getHour());
            // String minute = String.valueOf(time.getMinute());

            // String cronExpression = "* " + minute + " " + hour + " * * *";

            // TaskRepeat(FEED_NAME, duration, cronExpression);

            break;
         case "weekly":
            LocalTime weekly_time = schedulerRequest.getTime();
            String weekly_hour = String.valueOf(weekly_time.getHour());
            String weekly_minute = String.valueOf(weekly_time.getMinute());

            List<DayOfWeek> dayofweeks = Optional.ofNullable(schedulerRequest.getDayOfWeeks())
                  .orElse(Collections.emptyList());

            String dayofweekCron = dayofweeks.stream()
                  .map(DayOfWeek::name)
                  .collect(Collectors.joining(","));

            String weekly_cronExpression = "* " + weekly_minute + " " + weekly_hour + " * * " + dayofweekCron;
            TaskRepeat(FEED_NAME, duration, weekly_cronExpression);
            break;
         case "monthly":
            // xử lý theo tháng năm
            LocalTime monthly_time = schedulerRequest.getTime();
            String monthly_hour = String.valueOf(monthly_time.getHour());
            String monthly_minute = String.valueOf(monthly_time.getMinute());

            List<DayOfWeek> monthly_dayofweeks = Optional.ofNullable(schedulerRequest.getDayOfWeeks())
                  .orElse(Collections.emptyList());

            String monthly_dayofweekCron = monthly_dayofweeks.stream()
                  .map(DayOfWeek::name)
                  .collect(Collectors.joining(","));

            List<LocalDate> dayofmonth = Optional.ofNullable(schedulerRequest.getDays())
                  .orElse(Collections.emptyList());

            String monthly_dayofmonthCron = "";

            String monthly_cronExpression = "* " + monthly_minute + " " + monthly_hour + " * * "
                  + monthly_dayofweekCron;

            TaskRepeat(FEED_NAME, duration, monthly_cronExpression);
            break;

         default:
            throw new IllegalArgumentException("Invalid scheduler type: " + schedulerType);
      }
   }

   public void TurnOffDeviceSchedule(String FEED_NAME) {
      ScheduledFuture<?> oldTask = tasks.get(FEED_NAME);
      if (oldTask != null && !oldTask.isCancelled()) {
         oldTask.cancel(true);
      }

      System.out.println("Turn off " + FEED_NAME + "device automation");
      // ScheduledFuture<?> newTask = taskScheduler.schedule(task, cronTrigger);
      // tasks.put(FEED_NAME, newTask);
   }

   // -----------------Manual----------------------------
   public void TurnOnDeviceManual(String FEED_NAME, String state) {
      publishMessage(FEED_NAME, state);
   }

   // -----------------Automated--------------------------
   // @Async
   public void RunDeviceWaterAuto(int LOWER, int UPPER) {
      String FEED_NAME = "water";
      HttpHeaders headers = new HttpHeaders();
      headers.set("X-AIO-Key", AIO_KEY);

      HttpEntity<String> entity = new HttpEntity<>(headers);
      String URL = "https://io.adafruit.com/api/v2/" + USERNAME + "/feeds/" + FEED_NAME
            + "/data/last";
      ResponseEntity<AdafruitResponse> response = restTemplate.exchange(URL,
            HttpMethod.GET, entity,
            AdafruitResponse.class);
      try {
         if (!client.isConnected()) {
            while (!client.isConnected())
               client.connect(options);
            client.subscribe(USERNAME + "/feeds/+");
         }
      } catch (MqttException e) {
         e.printStackTrace();
      }
      int val = Integer.parseInt(response.getBody().getValue());
      System.out.println(LOWER + " ----" + UPPER + "-----" + val);
      if (val < LOWER) {
         publishMessage(FEED_NAME, "1");
         try {
            Thread.sleep(30000);
         } catch (Exception e) {
            System.out.println(e);
         }
         publishMessage(FEED_NAME, "0");
      } else if (val > UPPER) {
         publishMessage(FEED_NAME, "1");
         try {
            Thread.sleep(10000);
         } catch (Exception e) {
            System.out.println(e);
         }
         publishMessage(FEED_NAME, "0");
      } else {
         publishMessage(FEED_NAME, "0");
      }

   }

   public void TurnOnDeviceWaterAuto(int LOWER, int UPPER) {
      try {
         Thread.sleep(5000);
      } catch (Exception e) {
         System.out.println(e);
      }
      waterRun = true;
      while (waterRun) {
         RunDeviceWaterAuto(LOWER, UPPER);
         try {
            Thread.sleep(10000);
         } catch (Exception e) {
            System.out.println(e);
         }
      }
   }

   public void TurnOffDeviceWaterAuto() {
      waterRun = false;
      publishMessage(FEED_WATER, "0");
   }

   // @Async
   public void RunDeviceFanAuto(int LOWER, int UPPER) {
      HttpHeaders headers = new HttpHeaders();
      headers.set("X-AIO-Key", AIO_KEY);

      HttpEntity<String> entity = new HttpEntity<>(headers);
      String URL = "https://io.adafruit.com/api/v2/" + USERNAME + "/feeds/" + FEED_LIGHT
            + "/data/last";
      ResponseEntity<AdafruitResponse> response = restTemplate.exchange(URL,
            HttpMethod.GET, entity,
            AdafruitResponse.class);
      int val = Integer.parseInt(response.getBody().getValue());
      System.out.println(LOWER + " ----" + UPPER + "-----" + val);
      String FEED_NAME = "fan";
      try {
         if (!client.isConnected()) {
            while (!client.isConnected())
               client.connect(options);
            client.subscribe(USERNAME + "/feeds/+");
         }
      } catch (MqttException e) {
         e.printStackTrace();
      }
      if (val < LOWER) {
         publishMessage(FEED_NAME, "1");
         try {
            Thread.sleep(30000);
         } catch (Exception e) {
            System.out.println(e);
         }
         publishMessage(FEED_NAME, "0");
      } else if (val > UPPER) {
         publishMessage(FEED_NAME, "1");
         try {
            Thread.sleep(10000);
         } catch (Exception e) {
            System.out.println(e);
         }
         publishMessage(FEED_NAME, "0");
      } else {
         publishMessage(FEED_NAME, "0");
      }
   }

   public void TurnOnDeviceFanAuto(int LOWER, int UPPER) {
      try {
         Thread.sleep(5000);
      } catch (Exception e) {
         System.out.println(e);
      }
      fanRun = true;
      while (fanRun) {
         RunDeviceFanAuto(LOWER, UPPER);
         try {
            Thread.sleep(10000);
         } catch (Exception e) {
            System.out.println(e);
         }
      }
   }

   public void TurnOffDeviceFanAuto() {
      fanRun = false;
      publishMessage(FEED_FAN, "0");
   }
   // ------------------------------------------------------

   // ---------------------------------------------------------------------------------------
   public List<Double> getAllDataFeed(String FEED_NAME) {
      HttpHeaders headers = new HttpHeaders();
      headers.set("X-AIO-Key", AIO_KEY);

      HttpEntity<String> entity = new HttpEntity<>(headers);

      String url = "https://io.adafruit.com/api/v2/" + USERNAME + "/feeds/" + FEED_NAME + "/data";

      ResponseEntity<FeedMqttResponse[]> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            FeedMqttResponse[].class);

      if (response.getBody() == null) {
         return List.of();
      }

      return Arrays.stream(response.getBody())
            .map(FeedMqttResponse::getValue)
            .collect(Collectors.toList());
   }

   public RangeValueMqttResponse getRangeValue(String FEED_NAME) {
      String id_block = "";

      if (FEED_NAME.equals(FEED_MOISTURE)) {
         id_block = "2731269";
      } else if (FEED_NAME.equals(FEED_HUMIDITY)) {
         id_block = "2761303";
      } else if (FEED_NAME.equals(FEED_TEMPERATURE)) {
         id_block = "2761302";
      } else if (FEED_NAME.equals(FEED_LIGHT)) {
         id_block = "2731268";
      }

      String url = "https://io.adafruit.com/api/v2/" + USERNAME + "/dashboards/" + DASHBOARD_KEY + "/blocks/"
            + id_block;

      HttpHeaders headers = new HttpHeaders();
      headers.set("X-AIO-Key", AIO_KEY);

      HttpEntity<String> entity = new HttpEntity<>(headers);

      ResponseEntity<RangeValueMqttResponse> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            RangeValueMqttResponse.class);
      return response.getBody();
   }

   public void UpdateRangeValue(String FEED_NAME, int minValue, int maxValue) {
      String id_block = "";

      if (FEED_NAME.equals(FEED_MOISTURE)) {
         id_block = "2731269";
      } else if (FEED_NAME.equals(FEED_HUMIDITY)) {
         id_block = "2761303";
      } else if (FEED_NAME.equals(FEED_TEMPERATURE)) {
         id_block = "2761302";
      } else if (FEED_NAME.equals(FEED_LIGHT)) {
         id_block = "2731268";
      }

      String url = "https://io.adafruit.com/api/v2/" + USERNAME + "/dashboards/" + DASHBOARD_KEY + "/blocks/"
            + id_block;

      String body = String.format("""
            {
              "properties": {
                "minValue": "%s",
                "maxValue": "%s"
              }
            }
            """, minValue, maxValue);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.set("X-AIO-Key", AIO_KEY);

      HttpEntity<String> entity = new HttpEntity<>(body, headers);
      RestTemplate restTemplate = new RestTemplate();

      try {
         restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
         System.out.println("Block " + id_block + " updated successfully!");
      } catch (HttpClientErrorException e) {
         System.out.println("Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
      }
   }
}
