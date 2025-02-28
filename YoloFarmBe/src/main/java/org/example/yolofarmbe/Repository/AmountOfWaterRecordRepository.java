package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.Entity.AmountOfWaterRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmountOfWaterRecordRepository extends RecordRepository<AmountOfWaterRecord> {
}
