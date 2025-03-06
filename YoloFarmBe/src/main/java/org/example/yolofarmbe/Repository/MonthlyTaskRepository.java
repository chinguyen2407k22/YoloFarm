package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.Entity.MonthlyTask;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyTaskRepository extends SchedulerRepository<MonthlyTask> {
}
