package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.Entity.DailyTask;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyTaskRepository extends SchedulerRepository<DailyTask> {
}
