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
    ArrayList<String> followers;
    ArrayList<String> following;
    //THe institutions which the user is following
    ArrayList<String> institutionsFollowing;
    boolean isBanned;

    public UserModel() {
        followers = new ArrayList<>();
        following = new ArrayList<>();
        institutionsFollowing = new ArrayList<>();
    }

    public UserModel(boolean isAdmin, String firstName, String secondName, String email, ArrayList<UserActivityModel> activities) {
        this.isAdmin = isAdmin;
        this.email = email;
        this.activities = activities;
        this.firstName = firstName;
        this.secondName = secondName;
        institutionsFollowing = new ArrayList<>();
    }


    public ArrayList<String> getInstitutionsFollowing() {
        return this.institutionsFollowing;
    }

    public void setInstitutionsFollowing(ArrayList<String> institutionsFollowing) {
        this.institutionsFollowing = institutionsFollowing;
    }

    public boolean isIsBanned() {
        return this.isBanned;
    }


    public ArrayList<String> getFollowers(){
        return this.followers;
    }

    /**
     * @param followers the followers to set
     */
    public void setFollowers(ArrayList<String> followers) {
        this.followers = followers;
    }

    /**
     * @return the following
     */
    public ArrayList<String> getFollowing() {
        return following;
    }

    /**
     * @param following the following to set
     */
    public void setFollowing(ArrayList<String> following) {
        this.following = following;
    }

    public String getSecondName() {
        return this.secondName;
    }
	public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getFirstName() {
        return this.firstName;
    }
	public void setSecondName(String name) {
        this.secondName = name;
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

    
    public boolean getIsBanned() {
        return this.isBanned;
    }

    public void setIsBanned(boolean isAdmin) {
        this.isBanned = isAdmin;
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
            ", email='" + getEmail() + "'" +
            ", activities='" + getActivities() + "'" +
            "}";
    }


}