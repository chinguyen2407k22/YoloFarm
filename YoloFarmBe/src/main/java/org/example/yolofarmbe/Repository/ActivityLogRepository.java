package org.example.yolofarmbe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.yolofarmbe.Entity.ActivityLog;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog,Integer> {
}
