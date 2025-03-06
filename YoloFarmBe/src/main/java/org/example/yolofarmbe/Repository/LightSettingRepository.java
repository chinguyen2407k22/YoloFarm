package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.Entity.LightSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LightSettingRepository <T extends LightSetting> extends JpaRepository<T, Integer> {
}
