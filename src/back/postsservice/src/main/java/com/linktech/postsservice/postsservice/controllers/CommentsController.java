package com.linktech.postsservice.postsservice.controllers;

import java.util.List;

import com.linktech.postsservice.postsservice.models.Comment;
import com.linktech.postsservice.postsservice.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/posts")
public class CommentsController {

    @Autowired
    private ICommentsRepository commentsRepository;

    @PostMapping()
    public Comment createPost(@RequestBody Comment post) {
        return commentsRepository.save(post);
    }
    
    @GetMapping("/getPostComments/{postId}")
    public List<Comment> gePostComments(@PathVariable("postId") String postId){
        return commentsRepository.findByPostId(postId);
    }

    @GetMapping(value="/{id}")
    public Comment getPost(@PathVariable("id") String id) {
        return commentsRepository.findById(id).get();
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