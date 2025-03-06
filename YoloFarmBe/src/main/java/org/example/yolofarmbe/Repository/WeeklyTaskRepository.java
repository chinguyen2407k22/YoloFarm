package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.Entity.WeeklyTask;
import org.springframework.stereotype.Repository;

import java.util.WeakHashMap;

@Repository
public interface WeeklyTaskRepository extends SchedulerRepository<WeeklyTask> {
}
