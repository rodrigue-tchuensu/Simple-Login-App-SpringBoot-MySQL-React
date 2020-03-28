package de.tchuensu.home.springbootserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 *
 * @autor  Rodrigue Tchuensu P.
 */

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String email;

    @JsonIgnore
    @Column(name = "password_hash")
    private String passwordHash;

    public User() {}
//It is the responsibility of the user of this class to initiate the user with a password hash and not a plaintext password
    public User(Long id, String username, String email, String passwordHash) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
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

    //It is the responsibility of the user of this class to give in a hashed password and not a plaintext password to setup this field.
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}
