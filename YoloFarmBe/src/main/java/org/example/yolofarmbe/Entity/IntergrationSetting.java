package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "intergration_setting")
public class IntergrationSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name ="soil_sensor")
    @Enumerated(EnumType.STRING)
    private State soilSensor;

    @Column(name="weather_api")
    @Enumerated(EnumType.STRING)
    private State weatherApi;

    @Column(name ="predict_analytic")
    @Enumerated(EnumType.STRING)
    private State predict;

    public State getPredict() {
        return predict;
    }

    @OneToOne()
    @JoinColumn(name = "farm_id",referencedColumnName = "id")
    private Farm farm;

    public void setPredict(State predict) {
        this.predict = predict;
    }

    public State getWeatherApi() {
        return weatherApi;
    }

    public void setWeatherApi(State weatherApi) {
        this.weatherApi = weatherApi;
    }

    public State getSoilSensor() {
        return soilSensor;
    }

    public void setSoilSensor(State soilSensor) {
        this.soilSensor = soilSensor;
    }

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
