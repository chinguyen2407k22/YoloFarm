package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;

@Entity
@Table(name ="light_automated")
public class LightAutomated extends LightSetting{
    @Column(name = "send_warning")
    @Enumerated(EnumType.STRING)
    private State sendWarning;

    @Column(name = "min")
    private int min;

    @Column(name = "max")
    private int max;

    @Column(name = "upper")
    private int upper;

    @Column(name = "lower")
    private int lower;

    public State getSendWarning() {
        return sendWarning;
    }

    public void setSendWarning(State sendWarning) {
        this.sendWarning = sendWarning;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getUpper() {
        return upper;
    }

    public void setUpper(int upper) {
        this.upper = upper;
    }

    public int getLower() {
        return lower;
    }

    public void setLower(int lower) {
        this.lower = lower;
    }
}
