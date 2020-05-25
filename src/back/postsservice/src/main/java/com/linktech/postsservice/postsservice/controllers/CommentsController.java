package com.linktech.postsservice.postsservice.controllers;

import java.util.*;
import java.util.NoSuchElementException;

import com.linktech.postsservice.postsservice.ApiClients.UserClient;
import com.linktech.postsservice.postsservice.models.Comment;
import com.linktech.postsservice.postsservice.models.ExternalModels.UserModel;
import com.linktech.postsservice.postsservice.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/comments")
public class CommentsController {

    @Autowired
    private ICommentsRepository commentsRepository;

    UserClient userClient = Feign.builder()
    .client(new OkHttpClient())
    .encoder(new GsonEncoder())
    .decoder(new GsonDecoder())
    .logger(new Slf4jLogger(UserClient.class))
    .target(UserClient.class, "http://localhost:2202/users");
    
    @PostMapping()
    public Comment createPost(@RequestBody Comment post) {
        return commentsRepository.save(post);
    }
     
    //Example of request: http://localhost:2303/comments/getPostComments/{postId}
    @GetMapping("/getPostComments/{postId}")
    public ResponseEntity<List<Comment>> gePostComments(@PathVariable("postId") String postId){
       
        try  {
            List<Comment> comments = commentsRepository.findByPostId(postId);
            return new ResponseEntity<>(comments, HttpStatus.OK);

       }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    //Example of request: http://localhost:2303/comments/{id}
    @GetMapping(value="/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable("id") String id) {
        try  {
            Comment comment = commentsRepository.findById(id).get();
            return new ResponseEntity<>(comment, HttpStatus.OK);
       }catch (NoSuchElementException e){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @PutMapping(value="/{currentUserId}/{id}")
    public ResponseEntity<Comment> updatePost(@PathVariable("currentUserId") String currentUserId, @PathVariable("id") String id, @RequestBody Comment comment) {
        Comment thiscomment = commentsRepository.findById(id).get();
        if (thiscomment.getUserId() == currentUserId)
            comment.setId(id);
        else
        {
            UserModel userModel = userClient.get(currentUserId);
            if (userModel.isAdmin)
                comment.setId(id);
            else 
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<Comment>(commentsRepository.save(comment), HttpStatus.OK);
    }
    
    @DeleteMapping("/{currentUserId}/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("currentUserId") String currentUserId, 
        @PathVariable("id") String id)
    {
        Comment thiscomment = commentsRepository.findById(id).get();
        if (thiscomment.getUserId() == currentUserId)
            commentsRepository.deleteById(id);
        else
        {
            UserModel userModel = userClient.get(currentUserId);
            if (userModel.isAdmin)
                commentsRepository.deleteById(id);
            else 
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Comment>(HttpStatus.OK);
    }
}