package com.ignitis.blog.controllers;

import com.ignitis.blog.entities.BlogPost;
import com.ignitis.blog.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    BlogPostRepository blogPostRepository;

    @PostMapping("/blog/new")
    public ResponseEntity<String> newPost(@RequestBody BlogPost blogPost){
        blogPostRepository.save(blogPost);
        return new ResponseEntity<String>("Post successful", HttpStatus.OK);
    }

    @DeleteMapping("/blog/delete")
    public ResponseEntity<String> deletePost(@RequestParam("id") long id){
        blogPostRepository.deleteById(id);
        return new ResponseEntity<String>("Delete successful", HttpStatus.OK);
    }

    @GetMapping("/blog/posts")
    public List<BlogPost> getUserPosts(@RequestParam("user") String email){
        return blogPostRepository.findByAuthor(email);
    }

    @PutMapping("/blog/update")
    public ResponseEntity<String> updatePost(@RequestBody BlogPost blogPost){
        blogPostRepository.save(blogPost);
        return new ResponseEntity<String>("Update successful", HttpStatus.OK);
    }
}
