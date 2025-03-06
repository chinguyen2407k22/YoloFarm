package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "user_account")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
    @Id
    @Nationalized
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Nationalized
    @Column(name = "email")
    private String email;

    @Nationalized
    @Column(name = "firstname", length = 50)
    private String firstname;

    @Nationalized
    @Column(name = "lastname", length = 50)
    private String lastname;

    @Nationalized
    @Column(name = "hash_password")
    private String hashPassword;

    @ManyToOne()
    @JoinColumn(name = "farm_id")
    private Farm farm;

    private UserRole userRole;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}