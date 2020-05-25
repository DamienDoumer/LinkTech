package com.linktech.postsservice.postsservice.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Post extends BaseModel{
    //Post made by an institution: like job posts etc.
    private String institutionId;


    public String getInstitutionId() {
        return this.institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

}