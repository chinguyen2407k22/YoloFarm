package org.example.yolofarmbe.Factory;

import org.example.yolofarmbe.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class IrrigationSettingFactory {
    @Autowired
    private IrrigationManualRepository irrigationManualRepository;

    @Autowired
    private IrrigationAutomatedRepository irrigationAutomatedRepository;

    @Autowired
    private IrrigationScheduledRepository irrigationScheduledRepository;

    public <T extends JpaRepository<?, Integer>> T getIrrigationSettingRepository(Class<T> repoClass) {
        if (repoClass == IrrigationAutomatedRepository.class) {
            return repoClass.cast(irrigationAutomatedRepository);
        }else if (repoClass == IrrigationManualRepository.class) {
            return repoClass.cast(irrigationManualRepository);
        } else if (repoClass == IrrigationScheduledRepository.class) {
            return repoClass.cast(irrigationScheduledRepository);
        }
        throw new IllegalArgumentException("Invalid record type: " + repoClass);
    }
}
