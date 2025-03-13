package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.Entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Integer> {
    List<Device> findByFarm_Id(int farm_id);
}
