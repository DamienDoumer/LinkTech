package com.linktech.userservice.userservice.models;

import java.util.Date;

public class UserActivityModel{
    Date startDate;
    Date endDate;
    /**
     * The id of the institution concerned by this activity
     * Where institution is the school or company which is concerned 
     * with this activity
     */
    String institutionId;
    ActivityType activityType;

    Date getStartDate(){
        return startDate;
    }
    void setStartDate(Date date){
        endDate = date;
    }
    Date getEndDate(){
        return startDate;
    }
    void setEndDate(Date date){
        endDate = date;
    }
    String getinstitutionId(){
        return institutionId;
    }
    void setinstitutionId(String inst){
        institutionId = inst;
    }
    ActivityType getActivityType(){
        return activityType;
    }
    void setinstitutionId(ActivityType act){
        activityType = act;
    }

}
