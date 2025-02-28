package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;
import org.example.yolofarmbe.Entity.Record;

@Entity
@Table(name = "amount_of_water_record")
public class AmountOfWaterRecord extends Record {
    @Column(name = "record_value")
    private Double recordValue;

    public void setRecordValue(Double recordValue) {
        this.recordValue = recordValue;
    }

    public Double getRecordValue() {
        return recordValue;
    }
}