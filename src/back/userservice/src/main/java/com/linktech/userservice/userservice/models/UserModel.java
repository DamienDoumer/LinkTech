package com.linktech.userservice.userservice.models;

import java.util.ArrayList;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserModel extends BaseModel {

    boolean isAdmin;
    String firstName;
    String secondName;
    String email;
    ArrayList<UserActivityModel> activities;

    public UserModel() {
    }

    public UserModel(boolean isAdmin, String firstName, String secondName, String email, ArrayList<UserActivityModel> activities) {
        this.isAdmin = isAdmin;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.activities = activities;
    }

    public boolean isIsAdmin() {
        return this.isAdmin;
    }

    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return this.secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<UserActivityModel> getActivities() {
        return this.activities;
    }

    public void setActivities(ArrayList<UserActivityModel> activities) {
        this.activities = activities;
    }

    public UserModel isAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }

    public UserModel firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserModel secondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public UserModel email(String email) {
        this.email = email;
        return this;
    }

    public UserModel activities(ArrayList<UserActivityModel> activities) {
        this.activities = activities;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " isAdmin='" + isIsAdmin() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", secondName='" + getSecondName() + "'" +
            ", email='" + getEmail() + "'" +
            ", activities='" + getActivities() + "'" +
            "}";
    }


}