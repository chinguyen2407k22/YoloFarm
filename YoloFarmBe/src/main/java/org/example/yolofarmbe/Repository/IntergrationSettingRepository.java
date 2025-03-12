package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.Entity.ActivityLog;
import org.example.yolofarmbe.Entity.IntergrationSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntergrationSettingRepository extends JpaRepository<IntergrationSetting,Integer> {
    List<IntergrationSetting> findByFarm_Id(int farm_id);
}
