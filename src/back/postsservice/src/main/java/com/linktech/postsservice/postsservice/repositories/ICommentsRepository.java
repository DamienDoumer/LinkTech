package com.linktech.postsservice.postsservice.repositories;

import java.util.List;

import com.linktech.postsservice.postsservice.models.*;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentsRepository extends MongoRepository<Comment, String> {

    List<Comment> findByPostId(String postId);

}