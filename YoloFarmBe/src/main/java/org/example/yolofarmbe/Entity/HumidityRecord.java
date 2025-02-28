package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "humidity_record")
public class HumidityRecord extends Record {
    @Column(name = "record_value")
    private Double recordValue;

    public Double getRecordValue() {
        return recordValue;
    }

    public void setRecordValue(Double recordValue) {
        this.recordValue = recordValue;
    }

}