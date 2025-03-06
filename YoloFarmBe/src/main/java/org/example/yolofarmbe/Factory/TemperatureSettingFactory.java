package org.example.yolofarmbe.Factory;

import org.example.yolofarmbe.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class TemperatureSettingFactory {
    @Autowired
    private TemperatureAutomatedRepository temperatureAutomatedRepository;

    @Autowired
    private TemperatureManualRepository temperatureManualRepository;

    @Autowired
    private TemperatureScheduledRepository temperatureScheduledRepository;

    public <T extends JpaRepository<?, Integer>> T getTempSettingRepository(Class<T> repoClass) {
        if (repoClass == TemperatureScheduledRepository.class) {
            return repoClass.cast(temperatureScheduledRepository);
        }else if (repoClass == TemperatureAutomatedRepository.class) {
            return repoClass.cast(temperatureAutomatedRepository);
        } else if (repoClass == TemperatureManualRepository.class) {
            return repoClass.cast(temperatureManualRepository);
        }
        throw new IllegalArgumentException("Invalid record type: " + repoClass);
    }
}
