package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.Entity.LightRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LightRecordRepository extends RecordRepository<LightRecord> {
}
