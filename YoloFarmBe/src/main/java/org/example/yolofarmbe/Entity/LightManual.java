package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "light_manual")
public class LightManual extends LightSetting{
    @Column(name = "turn_on")
    @Enumerated(EnumType.STRING)
    private State sendWarning;
}
