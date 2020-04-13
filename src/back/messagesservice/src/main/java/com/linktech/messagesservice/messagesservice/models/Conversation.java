package com.linktech.messagesservice.messagesservice.models;

import java.util.Date;
import java.util.Objects;

public class Conversation {

    private String id;
    private String text;
    private Date creationDate;
    private String user1Id;
    private String user2Id;


    public Conversation() {
    }

    public Conversation(String id, String text, Date creationDate, String user1Id, String user2Id) {
        this.id = id;
        this.text = text;
        this.creationDate = creationDate;
        this.user1Id = user1Id;
        this.user2Id = user2Id;
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

    public String getUser1Id() {
        return this.user1Id;
    }

    public void setUser1Id(String user1Id) {
        this.user1Id = user1Id;
    }

    public String getUser2Id() {
        return this.user2Id;
    }

    public void setUser2Id(String user2Id) {
        this.user2Id = user2Id;
    }

    public Conversation id(String id) {
        this.id = id;
        return this;
    }

    public Conversation text(String text) {
        this.text = text;
        return this;
    }

    public Conversation creationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public Conversation user1Id(String user1Id) {
        this.user1Id = user1Id;
        return this;
    }

    public Conversation user2Id(String user2Id) {
        this.user2Id = user2Id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Conversation)) {
            return false;
        }
        Conversation conversation = (Conversation) o;
        return Objects.equals(id, conversation.id) && Objects.equals(text, conversation.text) && Objects.equals(creationDate, conversation.creationDate) && Objects.equals(user1Id, conversation.user1Id) && Objects.equals(user2Id, conversation.user2Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, creationDate, user1Id, user2Id);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", text='" + getText() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", user1Id='" + getUser1Id() + "'" +
            ", user2Id='" + getUser2Id() + "'" +
            "}";
    }

}