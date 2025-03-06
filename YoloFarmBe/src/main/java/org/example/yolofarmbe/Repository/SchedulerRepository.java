package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.Entity.Scheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerRepository <T extends Scheduler> extends JpaRepository<T,Integer> {
}
