package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.DTO.ActivityLogView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.example.yolofarmbe.Entity.ActivityLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog,Integer> {
    List<ActivityLogView> findByUserAccountUsername(String username);

    @Query("SELECT a.category AS category, a.title AS title, a.mode AS mode, a.logTime AS logTime, a.userAccount AS username FROM ActivityLog a")
    List<ActivityLogView> findAllActivityLog();

    Optional<ActivityLogView> findActivityLogById(int id);
}
