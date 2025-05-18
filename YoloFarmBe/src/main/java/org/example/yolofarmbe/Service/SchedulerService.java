package org.example.yolofarmbe.Service;

import org.example.yolofarmbe.Entity.*;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Factory.SchedulerFactory;
import org.example.yolofarmbe.Repository.*;
import org.example.yolofarmbe.Request.SchedulerRequest;
import org.example.yolofarmbe.Response.LightSettingResponse;
import org.example.yolofarmbe.Response.SchedulerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Locale;

@Service
public class SchedulerService {
    @Autowired
    private SchedulerFactory schedulerFactory;

    @Autowired
    private LightScheduledRepository lightScheduledRepository;

    @Autowired
    private TemperatureScheduledRepository temperatureScheduledRepository;

    @Autowired
    private IrrigationScheduledRepository irrigationScheduledRepository;

    @Autowired
    private MqttService mqttservice;

    public List<? extends Scheduler> getAllScheduler(String schedulerType) {
        switch (schedulerType.toLowerCase()) {
            case "daily":
                DailyTaskRepository dailyTaskRepository = schedulerFactory
                        .getSchedulerRepository(DailyTaskRepository.class);
                return dailyTaskRepository.findAll();
            case "weekly":
                WeeklyTaskRepository weeklyTaskRepository = schedulerFactory
                        .getSchedulerRepository(WeeklyTaskRepository.class);
                return weeklyTaskRepository.findAll();
            case "monthly":
                MonthlyTaskRepository monthlyTaskRepository = schedulerFactory
                        .getSchedulerRepository(MonthlyTaskRepository.class);
                return monthlyTaskRepository.findAll();
            default:
                throw new IllegalArgumentException("Invalid scheduler type: " + schedulerType);
        }
    }

    public SchedulerResponse<? extends Scheduler> addScheduler(String schedulerType,
            SchedulerRequest schedulerRequest) {
        switch (schedulerType.toLowerCase()) {
            case "daily":
                DailyTaskRepository dailyTaskRepository = schedulerFactory
                        .getSchedulerRepository(DailyTaskRepository.class);
                DailyTask dailyTask = new DailyTask();
                if (schedulerRequest.getLightScheduled() != null) {
                    mqttservice.TurnOnDeviceSchedule("fan", "daily", schedulerRequest);
                    int sid = schedulerRequest.getLightScheduled().getId();
                    LightScheduled lightScheduled = lightScheduledRepository.findById(sid)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Light Setting with id " + sid + " doesn't exist!"));
                    dailyTask.setLightScheduled(lightScheduled);
                }
                if (schedulerRequest.getTemperatureScheduled() != null) {
                    mqttservice.TurnOnDeviceSchedule("water", "daily", schedulerRequest);
                    int tid = schedulerRequest.getTemperatureScheduled().getId();
                    TemperatureScheduled temperatureScheduled = temperatureScheduledRepository.findById(tid)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Temperature Setting with id " + tid + " doesn't exist!"));
                    dailyTask.setTemperatureScheduled(temperatureScheduled);
                }
                if (schedulerRequest.getIrrigationScheduled() != null) {
                    int iid = schedulerRequest.getIrrigationScheduled().getId();
                    IrrigationScheduled irrigationScheduled = irrigationScheduledRepository.findById(iid)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Irrigation Setting with id " + iid + " doesn't exist!"));
                    dailyTask.setIrrigationScheduled(irrigationScheduled);
                }

                if (schedulerRequest.getDuration() != 0) {
                    dailyTask.setDuration(schedulerRequest.getDuration());
                }
                if (schedulerRequest.getTime() != null) {
                    dailyTask.setTime(schedulerRequest.getTime());
                }
                dailyTaskRepository.save(dailyTask);
                return SchedulerResponse.builder()
                        .message("Add daily task successfully!")
                        .type(schedulerType)
                        .scheduler(dailyTask)
                        .build();
            case "weekly":
                WeeklyTaskRepository weeklyTaskRepository = schedulerFactory
                        .getSchedulerRepository(WeeklyTaskRepository.class);
                WeeklyTask weeklyTask = new WeeklyTask();
                if (schedulerRequest.getLightScheduled() != null) {
                    mqttservice.TurnOnDeviceSchedule("fan", "daily", schedulerRequest);
                    int sid = schedulerRequest.getLightScheduled().getId();
                    LightScheduled lightScheduled = lightScheduledRepository.findById(sid)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Light Setting with id " + sid + " doesn't exist!"));
                    weeklyTask.setLightScheduled(lightScheduled);
                }
                if (schedulerRequest.getTemperatureScheduled() != null) {
                    mqttservice.TurnOnDeviceSchedule("fan", "daily", schedulerRequest);
                    int tid = schedulerRequest.getTemperatureScheduled().getId();
                    TemperatureScheduled temperatureScheduled = temperatureScheduledRepository.findById(tid)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Temperature Setting with id " + tid + " doesn't exist!"));
                    weeklyTask.setTemperatureScheduled(temperatureScheduled);
                }
                if (schedulerRequest.getIrrigationScheduled() != null) {
                    int iid = schedulerRequest.getIrrigationScheduled().getId();
                    IrrigationScheduled irrigationScheduled = irrigationScheduledRepository.findById(iid)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Irrigation Setting with id " + iid + " doesn't exist!"));
                    weeklyTask.setIrrigationScheduled(irrigationScheduled);
                }
                if (schedulerRequest.getTime() != null) {
                    weeklyTask.setTime(schedulerRequest.getTime());
                }
                if (schedulerRequest.getDayOfWeeks() != null) {
                    weeklyTask.setDateList(schedulerRequest.getDayOfWeeks());
                }
                if (schedulerRequest.getDuration() != 0) {
                    weeklyTask.setDuration(schedulerRequest.getDuration());
                }
                weeklyTaskRepository.save(weeklyTask);
                return SchedulerResponse.builder()
                        .message("Add weekly task successfully!")
                        .type(schedulerType)
                        .scheduler(weeklyTask)
                        .build();
            case "monthly":
                MonthlyTaskRepository monthlyTaskRepository = schedulerFactory
                        .getSchedulerRepository(MonthlyTaskRepository.class);
                MonthlyTask monthlyTask = new MonthlyTask();
                if (schedulerRequest.getLightScheduled() != null) {
                    mqttservice.TurnOnDeviceSchedule("fan", "daily", schedulerRequest);
                    int sid = schedulerRequest.getLightScheduled().getId();
                    LightScheduled lightScheduled = lightScheduledRepository.findById(sid)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Light Setting with id " + sid + " doesn't exist!"));
                    monthlyTask.setLightScheduled(lightScheduled);
                }
                if (schedulerRequest.getTemperatureScheduled() != null) {
                    mqttservice.TurnOnDeviceSchedule("fan", "daily", schedulerRequest);
                    int tid = schedulerRequest.getTemperatureScheduled().getId();
                    TemperatureScheduled temperatureScheduled = temperatureScheduledRepository.findById(tid)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Temperature Setting with id " + tid + " doesn't exist!"));
                    monthlyTask.setTemperatureScheduled(temperatureScheduled);
                }
                if (schedulerRequest.getIrrigationScheduled() != null) {
                    int iid = schedulerRequest.getIrrigationScheduled().getId();
                    IrrigationScheduled irrigationScheduled = irrigationScheduledRepository.findById(iid)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Irrigation Setting with id " + iid + " doesn't exist!"));
                    monthlyTask.setIrrigationScheduled(irrigationScheduled);
                }
                if (schedulerRequest.getDays() != null) {
                    monthlyTask.setDateList(schedulerRequest.getDays());
                }
                if (schedulerRequest.getDuration() != 0) {
                    monthlyTask.setDuration(schedulerRequest.getDuration());
                }
                if (schedulerRequest.getTime() != null) {
                    monthlyTask.setTime(schedulerRequest.getTime());
                }
                monthlyTaskRepository.save(monthlyTask);
                return SchedulerResponse.builder()
                        .message("Add monthly task successfully!")
                        .scheduler(monthlyTask)
                        .type(schedulerType)
                        .build();
            default:
                throw new IllegalArgumentException("Invalid scheduler type: " + schedulerType);
        }
    }

    public SchedulerResponse<? extends Scheduler> getSchedulerById(String schedulerType, int id) {
        switch (schedulerType.toLowerCase()) {
            case "daily":
                DailyTaskRepository dailyTaskRepository = schedulerFactory
                        .getSchedulerRepository(DailyTaskRepository.class);
                DailyTask dailyTask = dailyTaskRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Daily Task with id " + id + " doesn't exist!"));
                return SchedulerResponse.builder()
                        .message("Get daily task successfully!")
                        .scheduler(dailyTask)
                        .type(schedulerType)
                        .build();
            case "weekly":
                WeeklyTaskRepository weeklyTaskRepository = schedulerFactory
                        .getSchedulerRepository(WeeklyTaskRepository.class);
                WeeklyTask weeklyTask = weeklyTaskRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Weekly Task with id " + id + " doesn't exist!"));
                return SchedulerResponse.builder()
                        .message("Get weekly task successfully!")
                        .scheduler(weeklyTask)
                        .type(schedulerType)
                        .build();
            case "monthly":
                MonthlyTaskRepository monthlyTaskRepository = schedulerFactory
                        .getSchedulerRepository(MonthlyTaskRepository.class);
                MonthlyTask monthlyTask = monthlyTaskRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Monthly Task with id " + id + " doesn't exist!"));
                return SchedulerResponse.builder()
                        .message("Get monthly task successfully!")
                        .scheduler(monthlyTask)
                        .type(schedulerType)
                        .build();
            default:
                throw new IllegalArgumentException("Invalid scheduler type: " + schedulerType);
        }
    }

    public SchedulerResponse<? extends Scheduler> updateScheduler(String schedulerType, int id,
            SchedulerRequest schedulerRequest) {
        switch (schedulerType.toLowerCase()) {
            case "daily":
                DailyTaskRepository dailyTaskRepository = schedulerFactory
                        .getSchedulerRepository(DailyTaskRepository.class);
                DailyTask dailyTask = dailyTaskRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Daily Task with id " + id + " doesn't exist!"));
                if (schedulerRequest.getLightScheduled() != null) {
                    int sid = schedulerRequest.getLightScheduled().getId();
                    LightScheduled lightScheduled = lightScheduledRepository.findById(sid)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Light Setting with id " + sid + " doesn't exist!"));
                    dailyTask.setLightScheduled(lightScheduled);
                }
                if (schedulerRequest.getTemperatureScheduled() != null) {
                    int tid = schedulerRequest.getTemperatureScheduled().getId();
                    TemperatureScheduled temperatureScheduled = temperatureScheduledRepository.findById(tid)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Temperature Setting with id " + tid + " doesn't exist!"));
                    dailyTask.setTemperatureScheduled(temperatureScheduled);
                }
                if (schedulerRequest.getIrrigationScheduled() != null) {
                    int iid = schedulerRequest.getIrrigationScheduled().getId();
                    IrrigationScheduled irrigationScheduled = irrigationScheduledRepository.findById(iid)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Irrigation Setting with id " + iid + " doesn't exist!"));
                    dailyTask.setIrrigationScheduled(irrigationScheduled);
                }
                if (schedulerRequest.getDuration() != 0) {
                    dailyTask.setDuration(schedulerRequest.getDuration());
                }
                if (schedulerRequest.getTime() != null) {
                    dailyTask.setTime(schedulerRequest.getTime());
                }
                dailyTaskRepository.save(dailyTask);
                return SchedulerResponse.builder()
                        .message("Update daily task successfully!")
                        .scheduler(dailyTask)
                        .type(schedulerType)
                        .build();
            case "weekly":
                WeeklyTaskRepository weeklyTaskRepository = schedulerFactory
                        .getSchedulerRepository(WeeklyTaskRepository.class);
                WeeklyTask weeklyTask = weeklyTaskRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Weekly Task with id " + id + " doesn't exist!"));
                if (schedulerRequest.getLightScheduled() != null) {
                    int sid = schedulerRequest.getLightScheduled().getId();
                    LightScheduled lightScheduled = lightScheduledRepository.findById(sid)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Light Setting with id " + sid + " doesn't exist!"));
                    weeklyTask.setLightScheduled(lightScheduled);
                }
                if (schedulerRequest.getTemperatureScheduled() != null) {
                    int tid = schedulerRequest.getTemperatureScheduled().getId();
                    TemperatureScheduled temperatureScheduled = temperatureScheduledRepository.findById(tid)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Temperature Setting with id " + tid + " doesn't exist!"));
                    weeklyTask.setTemperatureScheduled(temperatureScheduled);
                }
                if (schedulerRequest.getIrrigationScheduled() != null) {
                    int iid = schedulerRequest.getIrrigationScheduled().getId();
                    IrrigationScheduled irrigationScheduled = irrigationScheduledRepository.findById(iid)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Irrigation Setting with id " + iid + " doesn't exist!"));
                    weeklyTask.setIrrigationScheduled(irrigationScheduled);
                }
                if (schedulerRequest.getTime() != null) {
                    weeklyTask.setTime(schedulerRequest.getTime());
                }
                if (schedulerRequest.getDayOfWeeks() != null) {
                    weeklyTask.setDateList(schedulerRequest.getDayOfWeeks());
                }
                if (schedulerRequest.getDuration() != 0) {
                    weeklyTask.setDuration(schedulerRequest.getDuration());
                }
                weeklyTaskRepository.save(weeklyTask);
                return SchedulerResponse.builder()
                        .message("Update weekly task successfully!")
                        .scheduler(weeklyTask)
                        .type(schedulerType)
                        .build();
            case "monthly":
                MonthlyTaskRepository monthlyTaskRepository = schedulerFactory
                        .getSchedulerRepository(MonthlyTaskRepository.class);
                MonthlyTask monthlyTask = monthlyTaskRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Monthly Task with id " + id + " doesn't exist!"));
                if (schedulerRequest.getLightScheduled() != null) {
                    int sid = schedulerRequest.getLightScheduled().getId();
                    LightScheduled lightScheduled = lightScheduledRepository.findById(sid)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Light Setting with id " + sid + " doesn't exist!"));
                    monthlyTask.setLightScheduled(lightScheduled);
                }
                if (schedulerRequest.getTemperatureScheduled() != null) {
                    int tid = schedulerRequest.getTemperatureScheduled().getId();
                    TemperatureScheduled temperatureScheduled = temperatureScheduledRepository.findById(tid)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Temperature Setting with id " + tid + " doesn't exist!"));
                    monthlyTask.setTemperatureScheduled(temperatureScheduled);
                }
                if (schedulerRequest.getIrrigationScheduled() != null) {
                    int iid = schedulerRequest.getIrrigationScheduled().getId();
                    IrrigationScheduled irrigationScheduled = irrigationScheduledRepository.findById(iid)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Irrigation Setting with id " + iid + " doesn't exist!"));
                    monthlyTask.setIrrigationScheduled(irrigationScheduled);
                }
                if (schedulerRequest.getDays() != null) {
                    monthlyTask.setDateList(schedulerRequest.getDays());
                }
                if (schedulerRequest.getDuration() != 0) {
                    monthlyTask.setDuration(schedulerRequest.getDuration());
                }
                if (schedulerRequest.getTime() != null) {
                    monthlyTask.setTime(schedulerRequest.getTime());
                }
                monthlyTaskRepository.save(monthlyTask);
                return SchedulerResponse.builder()
                        .message("Update monthly task successfully!")
                        .scheduler(monthlyTask)
                        .type(schedulerType)
                        .build();
            default:
                throw new IllegalArgumentException("Invalid scheduler type: " + schedulerType);
        }
    }

    public SchedulerResponse<? extends Scheduler> deleteSchedulerById(String schedulerType, int id) {
        switch (schedulerType.toLowerCase()) {
            case "daily":
                DailyTaskRepository dailyTaskRepository = schedulerFactory
                        .getSchedulerRepository(DailyTaskRepository.class);
                DailyTask dailyTask = dailyTaskRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Daily Task with id " + id + " doesn't exist!"));
                dailyTaskRepository.delete(dailyTask);
                return SchedulerResponse.builder()
                        .message("Delete daily task successfully!")
                        .scheduler(dailyTask)
                        .type(schedulerType)
                        .build();
            case "weekly":
                WeeklyTaskRepository weeklyTaskRepository = schedulerFactory
                        .getSchedulerRepository(WeeklyTaskRepository.class);
                WeeklyTask weeklyTask = weeklyTaskRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Weekly Task with id " + id + " doesn't exist!"));
                weeklyTaskRepository.delete(weeklyTask);
                return SchedulerResponse.builder()
                        .message("Delete weekly task successfully!")
                        .scheduler(weeklyTask)
                        .type(schedulerType)
                        .build();
            case "monthly":
                MonthlyTaskRepository monthlyTaskRepository = schedulerFactory
                        .getSchedulerRepository(MonthlyTaskRepository.class);
                MonthlyTask monthlyTask = monthlyTaskRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Monthly Task with id " + id + " doesn't exist!"));
                monthlyTaskRepository.delete(monthlyTask);
                return SchedulerResponse.builder()
                        .message("Delete monthly task successfully!")
                        .scheduler(monthlyTask)
                        .type(schedulerType)
                        .build();
            default:
                throw new IllegalArgumentException("Invalid scheduler type: " + schedulerType);
        }
    }
}
