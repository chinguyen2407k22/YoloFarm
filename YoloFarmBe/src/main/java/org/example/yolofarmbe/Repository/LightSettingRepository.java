package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.Entity.LightSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LightSettingRepository <T extends LightSetting> extends JpaRepository<T, Integer> {
    T findByFarm_Id(int farm_id);
}
