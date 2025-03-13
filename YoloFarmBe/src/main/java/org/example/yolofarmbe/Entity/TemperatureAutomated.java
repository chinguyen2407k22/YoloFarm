package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;

@Entity
@Table(name ="temperature_automated")
public class TemperatureAutomated extends TemperatureSetting{
    @Column(name = "lower")
    private int lower;

    @Column(name = "upper")
    private int upper;

    @Column(name = "send_warning")
    @Enumerated(EnumType.STRING)
    private State sendWarning;

    public int getLower() {
        return lower;
    }

    public void setLower(int lower) {
        this.lower = lower;
    }

    public int getUpper() {
        return upper;
    }

    public void setUpper(int upper) {
        this.upper = upper;
    }

    public State getSendWarning() {
        return sendWarning;
    }

    public void setSendWarning(State sendWarning) {
        this.sendWarning = sendWarning;
    }
}
