package com.sofia.postservice.controller;


import com.sofia.postservice.model.Posts;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {
    @GetMapping(value="/{postId}")
    public Posts getPost(@PathVariable("postId") String postId) {
        //Adding hard coded value to model for testing
        Posts postOne = new Posts(postId, "Post description for" + postId);
        return postOne;
    }
}
