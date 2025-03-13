package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.Entity.IrrigationSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IrrigationSettingRepository <T extends IrrigationSetting> extends JpaRepository<T,Integer> {
    T findByFarm_Id(int farm_id);
}
