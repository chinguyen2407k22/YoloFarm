package org.example.yolofarmbe.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Nationalized;

import java.util.Objects;

@Embeddable
public class ReminderId implements java.io.Serializable {
    private static final long serialVersionUID = 4695932357135568683L;
    @Column(name = "id", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReminderId entity = (ReminderId) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.username, entity.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

}