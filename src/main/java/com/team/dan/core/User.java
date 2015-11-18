package com.team.dan.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.security.Principal;

/**
 * Author: Liam Lundy
 * Creation Date: 11/3/15.
 * <p>
 * Package: ${PACKAGE}
 * Project: ${PROJECT}
 */
public class User implements Principal {

    @JsonProperty
    @NotEmpty
    private int userId;

    @JsonProperty
    @NotEmpty
    private String email;

    @JsonProperty
    @NotEmpty
    private boolean isAdmin;

    @JsonProperty
    @NotEmpty
    private boolean isValid;

    @JsonProperty
    @NotEmpty
    private String firstName;

    @JsonProperty
    @NotEmpty
    private String lastName;

    @JsonProperty
    @NotEmpty
    private String password;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return getEmail();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (isAdmin != user.isAdmin) return false;
        if (isValid != user.isValid) return false;
        if (!email.equals(user.email)) return false;
        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        return password.equals(user.password);

    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + email.hashCode();
        result = 31 * result + (isAdmin ? 1 : 0);
        result = 31 * result + (isValid ? 1 : 0);
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                ", isValid=" + isValid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
