package pl.orlowski.sebastian.forumspring.dto;

import pl.orlowski.sebastian.forumspring.validator.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegistrationDto {

    @Size(min = 5, max = 20, message = "Login must have 5-20 characters")
    private String login;

    @ValidPassword(message = "Password must contain 5-20 characters length, uppercase, lowercase character and digit!")
    private String password;

    @NotEmpty(message = "Email cannot be empty!")
    @Email(message = "Wrong email format!")
    private String email;

    public UserRegistrationDto(String login, String password, String email) {
        super();
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public UserRegistrationDto() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
