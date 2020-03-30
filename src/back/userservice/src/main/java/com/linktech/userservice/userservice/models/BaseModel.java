package com.linktech.userservice.userservice.models;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BaseModel {
    @Id 
    private String id;
    private Date creationData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Date getCreationDate(){
        return creationData;
    }
    public void setCreationDate(Date date){
        creationData = date;
    }
}