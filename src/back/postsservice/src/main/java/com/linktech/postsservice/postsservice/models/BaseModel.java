package com.linktech.postsservice.postsservice.models;

import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BaseModel {
    
    @Id 
    private String id;
    private Date creationData;
    String content;
    String userId;


    public BaseModel() {
    }

    public BaseModel(String id, Date creationData, String content, String userId) {
        this.id = id;
        this.creationData = creationData;
        this.content = content;
        this.userId = userId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationData() {
        return this.creationData;
    }

    public void setCreationData(Date creationData) {
        this.creationData = creationData;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BaseModel id(String id) {
        this.id = id;
        return this;
    }

    public BaseModel creationData(Date creationData) {
        this.creationData = creationData;
        return this;
    }

    public BaseModel content(String content) {
        this.content = content;
        return this;
    }

    public BaseModel userId(String userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BaseModel)) {
            return false;
        }
        BaseModel baseModel = (BaseModel) o;
        return Objects.equals(id, baseModel.id) && Objects.equals(creationData, baseModel.creationData) && Objects.equals(content, baseModel.content) && Objects.equals(userId, baseModel.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationData, content, userId);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", creationData='" + getCreationData() + "'" +
            ", content='" + getContent() + "'" +
            ", userId='" + getUserId() + "'" +
            "}";
    }

}