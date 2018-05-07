package models;

import play.data.validation.Constraints;

import javax.validation.Constraint;

public class SignUp {

    @Constraints.Required
    private String email;
    @Constraints.Required
    private String fullName;
    @Constraints.Required
    private String password;

    public SignUp() {
        this.fullName = fullName;
        this.password = password;
        this.email = email;
    }

    public SignUp(String email, String fullName, String password) {
        this.fullName = fullName;
        this.password = password;
        this.email = email;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }
}
