package org.example.yolofarmbe.Factory;

import org.example.yolofarmbe.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class LightSettingFactory {
    @Autowired
    private LightScheduledRepository lightScheduledRepository;

    @Autowired
    private LightAutomatedRepository lightAutomatedRepository;

    @Autowired
    private LightManualRepository lightManualRepository;

    public <T extends JpaRepository<?, Integer>> T getLightSettingRepository(Class<T> repoClass) {
        if (repoClass == LightScheduledRepository.class) {
            return repoClass.cast(lightScheduledRepository);
        }else if (repoClass == LightAutomatedRepository.class) {
            return repoClass.cast(lightAutomatedRepository);
        } else if (repoClass == LightManualRepository.class) {
            return repoClass.cast(lightManualRepository);
        }
        throw new IllegalArgumentException("Invalid record type: " + repoClass);
    }
}
