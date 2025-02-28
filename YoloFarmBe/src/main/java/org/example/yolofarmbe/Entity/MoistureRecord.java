package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "moisture_record")
public class MoistureRecord extends Record {
    @Column(name = "record_value")
    private Double recordValue;

    public Double getRecordValue() {
        return recordValue;
    }

    public void setRecordValue(Double recordValue) {
        this.recordValue = recordValue;
    }

}