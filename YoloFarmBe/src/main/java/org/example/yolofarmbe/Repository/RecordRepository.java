package org.example.yolofarmbe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.yolofarmbe.Entity.Record;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository <T extends Record> extends JpaRepository<T, Integer> {
    List<T> findByFarm_Id(Integer farmId);
}
