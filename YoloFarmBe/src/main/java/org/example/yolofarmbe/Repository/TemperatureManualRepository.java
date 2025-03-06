package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.Entity.TemperatureManual;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureManualRepository extends TemperatureSettingRepository<TemperatureManual> {
}
