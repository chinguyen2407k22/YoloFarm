package org.example.yolofarmbe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.yolofarmbe.Entity.ActivityLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog,Integer> {
    List<ActivityLog> findByFarm_Id(int farm_id);
}
