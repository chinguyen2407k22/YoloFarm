package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "farm")
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "farm_size")
    private Double farmSize;

    @Nationalized
    @Column(name = "crop")
    private String crop;

    @Nationalized
    @Column(name = "farm_name", length = 100)
    private String farmName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getFarmSize() {
        return farmSize;
    }

    public void setFarmSize(Double farmSize) {
        this.farmSize = farmSize;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

}