package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.Entity.HumidityRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumidityRecordRepository extends RecordRepository<HumidityRecord> {
}
