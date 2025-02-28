package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.Entity.TemperatureRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRecordRepository extends RecordRepository<TemperatureRecord> {
}
