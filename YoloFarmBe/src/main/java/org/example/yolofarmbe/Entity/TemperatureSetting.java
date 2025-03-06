package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;

import javax.persistence.Table;

@Entity
@Table(name ="temperature_setting")
public abstract class TemperatureSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne()
    @JoinColumn(name = "farm_id",referencedColumnName = "id")
    private Farm farm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }
}
