package com.linktech.postsservice.postsservice.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Comment extends BaseModel {

    String postId;
}
