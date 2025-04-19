package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "reminder")
public class Reminder {
    @EmbeddedId
    private ReminderId id;

    @MapsId("username")
    @ManyToOne(optional = false)
    @JoinColumn(name = "username", nullable = false)
    private UserAccount username;

    @Nationalized
    @Column(name = "title")
    private String title;

    @Nationalized
    @Column(name = "reminder_description")
    private String reminderDescription;

    @Column(name = "reminder_time")
    private LocalDateTime reminderTime;

    @Column(name = "is_done")
    private Boolean isDone;

    public ReminderId getId() {
        return id;
    }

    public void setId(ReminderId id) {
        this.id = id;
    }

    public UserAccount getUsername() {
        return username;
    }

    public void setUsername(UserAccount username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReminderDescription() {
        return reminderDescription;
    }

    public void setReminderDescription(String reminderDescription) {
        this.reminderDescription = reminderDescription;
    }

    public LocalDateTime getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(LocalDateTime reminderTime) {
        this.reminderTime = reminderTime;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

}