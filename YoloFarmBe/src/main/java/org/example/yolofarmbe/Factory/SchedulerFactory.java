package org.example.yolofarmbe.Factory;

import org.example.yolofarmbe.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class SchedulerFactory {
    @Autowired
    private DailyTaskRepository dailyTaskRepository;

    @Autowired
    MonthlyTaskRepository monthlyTaskRepository;

    @Autowired
    WeeklyTaskRepository weeklyTaskRepository;

    public <T extends JpaRepository<?, Integer>> T getSchedulerRepository(Class<T> repoClass) {
        if (repoClass == DailyTaskRepository.class) {
            return repoClass.cast(dailyTaskRepository);
        }else if (repoClass == MonthlyTaskRepository.class) {
            return repoClass.cast(monthlyTaskRepository);
        } else if (repoClass == WeeklyTaskRepository.class) {
            return repoClass.cast(weeklyTaskRepository);
        }
        throw new IllegalArgumentException("Invalid record type: " + repoClass);
    }
}
