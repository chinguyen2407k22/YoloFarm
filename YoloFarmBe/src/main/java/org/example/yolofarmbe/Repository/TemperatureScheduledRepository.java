package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.Entity.TemperatureScheduled;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureScheduledRepository extends TemperatureSettingRepository<TemperatureScheduled> {
}
