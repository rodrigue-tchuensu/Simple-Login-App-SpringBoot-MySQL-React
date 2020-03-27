package de.tchuensu.home.springbootserver.dao.dto;

public class UserCredentialsDto {

    private String username;
    private String password;

    public UserCredentialsDto() {}

    public UserCredentialsDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
