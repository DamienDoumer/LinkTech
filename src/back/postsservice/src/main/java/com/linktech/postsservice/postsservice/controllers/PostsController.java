package com.linktech.postsservice.postsservice.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import com.linktech.postsservice.postsservice.models.Post;
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

@RestController
@RequestMapping(value = "/posts")
public class PostsController {

    @Autowired
    private IPostsRepository postsRepository;

    @PostMapping()
    public Post createPost(@RequestBody Post post) {
        return postsRepository.save(post);
    }


    //Example of request: http://localhost:2303/posts/getUserPosts/abc

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
    
    @PutMapping(value="/{id}")
    public Post updatePost(@PathVariable String id, @RequestBody Post post) {
        
        post.setId(id);
        return postsRepository.save(post);
    }
    
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("id") String id)
    {
        postsRepository.deleteById(id);
    }
}