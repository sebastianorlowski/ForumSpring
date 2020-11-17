package pl.orlowski.sebastian.forumspring.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegistrationDto {

    @Size(min = 5, max = 20, message = "Login must be beetwen 5 and 20 characters")
    private String login;
    @Size(min = 5, max = 20, message = "Password must be beetwen 5 and 20 characters")
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
