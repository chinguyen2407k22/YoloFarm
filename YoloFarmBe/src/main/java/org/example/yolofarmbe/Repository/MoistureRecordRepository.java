package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.Entity.MoistureRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoistureRecordRepository extends RecordRepository<MoistureRecord> {
}
