package org.example.yolofarmbe.Service;

import org.example.yolofarmbe.Entity.*;
import org.example.yolofarmbe.Entity.Record;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Factory.RecordRepositoryFactory;
import org.example.yolofarmbe.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    private RecordRepositoryFactory recordRepositoryFactory;
    private FarmRepository farmRepository;

    @Autowired
    public RecordService(RecordRepositoryFactory recordRepositoryFactory, FarmRepository farmRepository) {
        this.recordRepositoryFactory = recordRepositoryFactory;
        this.farmRepository = farmRepository;
    }

    public List<? extends Record> fetchRecords(String recordType) {
        switch (recordType.toLowerCase()) {
            case "amountofwater":
                AmountOfWaterRecordRepository amoWaterRepo = recordRepositoryFactory
                        .getRecordRepository(AmountOfWaterRecordRepository.class);
                return amoWaterRepo.findAll();
            case "moisture":
                MoistureRecordRepository moiRepo = recordRepositoryFactory
                        .getRecordRepository(MoistureRecordRepository.class);
                return moiRepo.findAll();
            case "light":
                LightRecordRepository lightRepo = recordRepositoryFactory
                        .getRecordRepository(LightRecordRepository.class);
                return lightRepo.findAll();
            case "humidity":
                HumidityRecordRepository humiRepo = recordRepositoryFactory
                        .getRecordRepository((HumidityRecordRepository.class));
                return humiRepo.findAll();
            case "temperature":
                TemperatureRecordRepository temRepo = recordRepositoryFactory
                        .getRecordRepository(TemperatureRecordRepository.class);
                return temRepo.findAll();
            default:
                throw new IllegalArgumentException("Invalid record type: " + recordType);
        }
    }

    public List<? extends Record> fetchRecordsByFarmId(String recordType, int farm_id) {
        Farm farm = farmRepository.findById(farm_id)
                .orElseThrow(() -> new ResourceNotFoundException("Farm with id " + farm_id + "not exist"));
        switch (recordType.toLowerCase()) {
            case "amountofwater":
                AmountOfWaterRecordRepository amoWaterRepo = recordRepositoryFactory
                        .getRecordRepository(AmountOfWaterRecordRepository.class);
                return amoWaterRepo.findByFarm_Id(farm_id);
            case "moisture":
                MoistureRecordRepository moiRepo = recordRepositoryFactory
                        .getRecordRepository(MoistureRecordRepository.class);
                return moiRepo.findByFarm_Id(farm_id);
            case "light":
                LightRecordRepository lightRepo = recordRepositoryFactory
                        .getRecordRepository(LightRecordRepository.class);
                return lightRepo.findByFarm_Id(farm_id);
            case "humidity":
                HumidityRecordRepository humiRepo = recordRepositoryFactory
                        .getRecordRepository((HumidityRecordRepository.class));
                return humiRepo.findByFarm_Id(farm_id);
            case "temperature":
                TemperatureRecordRepository temRepo = recordRepositoryFactory
                        .getRecordRepository(TemperatureRecordRepository.class);
                return temRepo.findByFarm_Id(farm_id);
            default:
                throw new IllegalArgumentException("Invalid record type: " + recordType);
        }
    }

    public void SaveRecords(String recordType, Record record) {
        switch (recordType.toLowerCase()) {
            case "water":
                AmountOfWaterRecordRepository amoWaterRepo = recordRepositoryFactory
                        .getRecordRepository(AmountOfWaterRecordRepository.class);
                amoWaterRepo.save((AmountOfWaterRecord) record);
                break;
            case "moisture":
                MoistureRecordRepository moiRepo = recordRepositoryFactory
                        .getRecordRepository(MoistureRecordRepository.class);
                moiRepo.save((MoistureRecord) record);
                break;
            case "light":
                LightRecordRepository lightRepo = recordRepositoryFactory
                        .getRecordRepository(LightRecordRepository.class);
                lightRepo.save((LightRecord) record);
                break;
            case "humidity":
                HumidityRecordRepository humiRepo = recordRepositoryFactory
                        .getRecordRepository((HumidityRecordRepository.class));
                humiRepo.save((HumidityRecord) record);

                break;
            case "temperature":
                TemperatureRecordRepository temRepo = recordRepositoryFactory
                        .getRecordRepository(TemperatureRecordRepository.class);
                temRepo.save((TemperatureRecord) record);
                break;
            default:
                throw new IllegalArgumentException("Invalid record type: " + recordType);
        }
    }

    public String deleteRecord(String recordType, int record_id) {
        switch (recordType.toLowerCase()) {
            case "amountofwater":
                AmountOfWaterRecordRepository amoWaterRepo = recordRepositoryFactory
                        .getRecordRepository(AmountOfWaterRecordRepository.class);
                AmountOfWaterRecord amountOfWaterRecord = amoWaterRepo.findById(record_id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Water record with id " + record_id + "not exist"));
                amoWaterRepo.delete(amountOfWaterRecord);
                return "deleted a Amount Of Water Record.";
            case "moisture":
                MoistureRecordRepository moiRepo = recordRepositoryFactory
                        .getRecordRepository(MoistureRecordRepository.class);
                MoistureRecord moistureRecord = moiRepo.findById(record_id)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Moisture record with id" + record_id + "not exist"));
                moiRepo.delete(moistureRecord);
                return "deleted a Moisture Record.";
            case "light":
                LightRecordRepository lightRepo = recordRepositoryFactory
                        .getRecordRepository(LightRecordRepository.class);
                LightRecord lightRecord = lightRepo.findById(record_id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Light record with id" + record_id + "not exist"));
                lightRepo.delete(lightRecord);
                return "deleted a Light Record.";
            case "humidity":
                HumidityRecordRepository humiRepo = recordRepositoryFactory
                        .getRecordRepository((HumidityRecordRepository.class));
                HumidityRecord humidityRecord = humiRepo.findById(record_id)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Humidity record with id" + record_id + "not exist"));
                humiRepo.delete(humidityRecord);
                return "deleted a Humidity Record.";
            case "temperature":
                TemperatureRecordRepository temRepo = recordRepositoryFactory
                        .getRecordRepository(TemperatureRecordRepository.class);
                TemperatureRecord temperatureRecord = temRepo.findById(record_id)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Tempurature record with id" + record_id + "not exist"));
                temRepo.delete(temperatureRecord);
                return "deleted a Tempurature Record.";
            default:
                throw new IllegalArgumentException("Invalid record type: " + recordType);
        }
    }
}
