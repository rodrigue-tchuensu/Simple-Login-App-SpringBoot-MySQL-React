package de.tchuensu.home.springbootserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

/**
 *
 * @autor  Rodrigue Tchuensu P.
 */

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    @JsonIgnore
    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "can_access_any_data" )
    private boolean canAccessAllData;

    public User() {}

    //It is the responsibility of the user of this class to initiate the user with a password hash and not a plaintext password
    public User(String username, String email, String passwordHash) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.canAccessAllData = false;
    }

    public User(String username, String email, String passwordHash, boolean canAccessAllData) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.canAccessAllData = canAccessAllData;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean isCanAccessAllData() {
        return canAccessAllData;
    }

    public void setCanAccessAllData(boolean canAccessAllData) {
        this.canAccessAllData = canAccessAllData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return isCanAccessAllData() == user.isCanAccessAllData() &&
                getId().equals(user.getId()) &&
                getUsername().equals(user.getUsername()) &&
                getEmail().equals(user.getEmail()) &&
                getPasswordHash().equals(user.getPasswordHash());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getEmail(), getPasswordHash(), isCanAccessAllData());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", canAccessAllData=" + canAccessAllData +
                '}';
    }
}
