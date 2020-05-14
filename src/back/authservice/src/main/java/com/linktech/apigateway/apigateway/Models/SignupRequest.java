package com.linktech.apigateway.apigateway.Models;

import java.util.Date;
import java.util.Objects;

public class SignupRequest {
    String userName;
    String email;
    String password;
    Date birthDate;
    String address;
    String firstName;
    String lastName;


    public SignupRequest() {
    }

    public SignupRequest(String userName, String email, String password, Date birthDate, String address) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.address = address;
    }


    public SignupRequest(String userName, String email, String password, Date birthDate, String address, String firstName, String lastName) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SignupRequest firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public SignupRequest lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }


    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public SignupRequest userName(String userName) {
        this.userName = userName;
        return this;
    }

    public SignupRequest email(String email) {
        this.email = email;
        return this;
    }

    public SignupRequest password(String password) {
        this.password = password;
        return this;
    }

    public SignupRequest birthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public SignupRequest address(String address) {
        this.address = address;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SignupRequest)) {
            return false;
        }
        SignupRequest signupRequest = (SignupRequest) o;
        return Objects.equals(userName, signupRequest.userName) && Objects.equals(email, signupRequest.email) && Objects.equals(password, signupRequest.password) && Objects.equals(birthDate, signupRequest.birthDate) && Objects.equals(address, signupRequest.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, email, password, birthDate, address);
    }

    @Override
    public String toString() {
        return "{" +
            " userName='" + getUserName() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }

}