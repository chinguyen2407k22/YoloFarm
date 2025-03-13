package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.Entity.TemperatureSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureSettingRepository < T extends TemperatureSetting> extends JpaRepository<T,Integer> {
    T findByFarm_Id(int id);
}
