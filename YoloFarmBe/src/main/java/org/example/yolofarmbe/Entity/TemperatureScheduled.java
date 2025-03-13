package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;

@Entity
@Table(name ="temperature_scheduled")
public class TemperatureScheduled extends TemperatureSetting{
    @Column(name = "send_warning")
    @Enumerated(EnumType.STRING)
    private State sendWarning;

    public State getSendWarning() {
        return sendWarning;
    }

    public void setSendWarning(State sendWarning) {
        this.sendWarning = sendWarning;
    }
}
