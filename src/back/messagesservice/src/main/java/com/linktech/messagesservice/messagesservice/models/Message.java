package com.linktech.messagesservice.messagesservice.models;

import java.util.Date;
import java.util.Objects;

public class Message {

    private String id;
    private String text;
    private Date creationDate;


    public Message() {
    }

    public Message(String id, String text, Date creationDate) {
        this.id = id;
        this.text = text;
        this.creationDate = creationDate;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Message id(String id) {
        this.id = id;
        return this;
    }

    public Message text(String text) {
        this.text = text;
        return this;
    }

    public Message creationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Message)) {
            return false;
        }
        Message message = (Message) o;
        return Objects.equals(id, message.id) && Objects.equals(text, message.text) && Objects.equals(creationDate, message.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, creationDate);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", text='" + getText() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            "}";
    }

}