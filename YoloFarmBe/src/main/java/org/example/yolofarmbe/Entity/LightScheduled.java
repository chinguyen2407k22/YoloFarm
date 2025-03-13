package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "light_scheduled")
public class LightScheduled extends LightSetting{
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
