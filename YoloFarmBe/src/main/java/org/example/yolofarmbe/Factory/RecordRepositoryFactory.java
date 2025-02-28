package org.example.yolofarmbe.Factory;


import org.example.yolofarmbe.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class RecordRepositoryFactory {

    @Autowired
    private AmountOfWaterRecordRepository amountOfWaterRecordRepository;

    @Autowired
    private LightRecordRepository lightRecordRepository;

    @Autowired
    private TemperatureRecordRepository temperatureRecordRepository;

    @Autowired
    private MoistureRecordRepository moistureRecordRepository;

    @Autowired
    private HumidityRecordRepository humidityRecordRepository;

    public <T extends JpaRepository<?, Integer>> T getRecordRepository(Class<T> repoClass) {
        if (repoClass == LightRecordRepository.class) {
            return repoClass.cast(lightRecordRepository);
        }else if (repoClass == MoistureRecordRepository.class) {
            return repoClass.cast(moistureRecordRepository);
        } else if (repoClass == TemperatureRecordRepository.class) {
            return repoClass.cast(temperatureRecordRepository);
        } else if (repoClass == AmountOfWaterRecordRepository.class) {
            return repoClass.cast(amountOfWaterRecordRepository);
        } else if (repoClass == HumidityRecordRepository.class) {
            return repoClass.cast(humidityRecordRepository);
        }
        throw new IllegalArgumentException("Invalid record type: " + repoClass);
    }


}
