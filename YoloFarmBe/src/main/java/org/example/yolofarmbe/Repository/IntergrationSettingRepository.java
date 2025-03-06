package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.Entity.IntergrationSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntergrationSettingRepository extends JpaRepository<IntergrationSetting,Integer> {
}
