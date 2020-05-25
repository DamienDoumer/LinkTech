package com.linktech.postsservice.postsservice.repositories;

import java.util.List;

import com.linktech.postsservice.postsservice.models.*;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface IPostsRepository extends MongoRepository<Post, String> {

    List<Post> findByUserId(String userId);
    List<Post> findByInstitutionId(String institutionId);
}