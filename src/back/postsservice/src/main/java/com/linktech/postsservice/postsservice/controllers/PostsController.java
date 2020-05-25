package com.linktech.postsservice.postsservice.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import com.linktech.postsservice.postsservice.ApiClients.UserClient;
import com.linktech.postsservice.postsservice.models.Post;
import com.linktech.postsservice.postsservice.models.ExternalModels.UserModel;
import com.linktech.postsservice.postsservice.repositories.IPostsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;


@RestController
@RequestMapping(value = "/posts")
public class PostsController {

    @Autowired
    private IPostsRepository postsRepository;

    UserClient userClient = Feign.builder()
    .client(new OkHttpClient())
    .encoder(new GsonEncoder())
    .decoder(new GsonDecoder())
    .logger(new Slf4jLogger(UserClient.class))
    .target(UserClient.class, "http://localhost:2202/users");
    
    //Post request: http://localhost:2303/posts/
    @PostMapping()
    public Post createPost(@Valid @RequestBody Post post) {
        return postsRepository.save(post);
    }

    @GetMapping("/getInstitutionsPosts/{institutionId}")
    public ResponseEntity<List<Post>> getInstitutionsPosts(@Valid @PathVariable("institutionId") String institutionId) {
        List<Post> posts = new ArrayList<Post>();
        try {
            
            posts = postsRepository.findByInstitutionId(institutionId);
            return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Example of request: http://localhost:2303/posts/getUserPosts/{userId}
    @GetMapping("/getUserPosts/{userId}")
    public ResponseEntity<List<Post>> getUserPosts(@Valid @PathVariable("userId") String userId) {
        List<Post> posts = new ArrayList<Post>();
        try {
            
            posts = postsRepository.findByUserId(userId);
            return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Example of request: http://localhost:2303/posts/{id}

    @GetMapping(value="/{id}")
    public ResponseEntity<Post> getPost(@PathVariable("id") String id) {
       //Catch when there is no element : NoSuchElementException
        try {
            Post post = postsRepository.findById(id).get();
            return new ResponseEntity<Post>(post, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping(value="/{userId}/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable String userId, @PathVariable String id, @RequestBody Post post) {
        
        Post thisPost = postsRepository.findById(id).get();
        if (thisPost.getUserId() == userId || thisPost.getInstitutionId() == userId)
            post.setId(id);
        else
        {
            UserModel userModel = userClient.get(userId);
            if (userModel.isAdmin)
                post.setId(id);
            else 
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Post>(postsRepository.save(post), HttpStatus.OK);
    }

    
    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<?> deletePost(@PathVariable String userId, @PathVariable("id") String id)
    {
        Post thisPost = postsRepository.findById(id).get();
        
        if (thisPost.getUserId() == userId || thisPost.getInstitutionId() == userId)
            postsRepository.deleteById(id);
        else
        {
            UserModel userModel = userClient.get(userId);
            if (userModel.isAdmin)
                postsRepository.deleteById(id);
            else 
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}