package com.linktech.postsservice.postsservice.controllers;

import java.util.List;

import com.linktech.postsservice.postsservice.models.Post;
import com.linktech.postsservice.postsservice.repositories.IPostsRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    @GetMapping("/getUserPosts/{userId}")
    public List<Post> geUserPosts(@PathVariable("userId") String userId){
        return postsRepository.findByUserId(userId);
    }

    @GetMapping(value="/{id}")
    public Post getPost(@PathVariable("id") String id) {
        return postsRepository.findById(id).get();
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