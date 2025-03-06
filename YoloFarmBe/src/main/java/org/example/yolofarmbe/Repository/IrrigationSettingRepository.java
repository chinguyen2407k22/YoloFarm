package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.Entity.IrrigationSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IrrigationSettingRepository <T extends IrrigationSetting> extends JpaRepository<T,Integer> {
}
