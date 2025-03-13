package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;

import java.time.Duration;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "scheduler")
public abstract class Scheduler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name ="duration")
    private long duration;

    @OneToOne
    @JoinColumn(name = "light_setting_id", referencedColumnName = "id")
    private LightScheduled lightScheduled;

    @OneToOne
    @JoinColumn(name = "temperature_setting_id", referencedColumnName = "id")
    private TemperatureScheduled temperatureScheduled;

    @OneToOne
    @JoinColumn(name = "irrigationSetting_setting_id", referencedColumnName = "id")
    private IrrigationScheduled irrigationScheduled;

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TemperatureScheduled getTemperatureScheduled() {
        return temperatureScheduled;
    }

    public void setTemperatureScheduled(TemperatureScheduled temperatureScheduled) {
        this.temperatureScheduled = temperatureScheduled;
    }

    public IrrigationScheduled getIrrigationScheduled() {
        return irrigationScheduled;
    }

    public void setIrrigationScheduled(IrrigationScheduled irrigationScheduled) {
        this.irrigationScheduled = irrigationScheduled;
    }

    public LightScheduled getLightScheduled() {
        return lightScheduled;
    }

    public void setLightScheduled(LightScheduled lightScheduled) {
        this.lightScheduled = lightScheduled;
    }
}
