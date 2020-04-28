package com.linktech.postsservice.postsservice.controllers;

import java.util.*;
import java.util.NoSuchElementException;

import com.linktech.postsservice.postsservice.models.Comment;
import com.linktech.postsservice.postsservice.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    
    @PutMapping(value="/{id}")
    public Comment updatePost(@PathVariable String id, @RequestBody Comment comment) {
        
        comment.setId(id);
        return commentsRepository.save(comment);
    }
    
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable("id") String id)
    {
        commentsRepository.deleteById(id);
    }
}