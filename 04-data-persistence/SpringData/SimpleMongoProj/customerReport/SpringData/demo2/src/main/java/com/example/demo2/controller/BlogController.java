package com.example.demo2.controller;

import com.example.demo2.entity.Comment;
import com.example.demo2.entity.Post;
import com.example.demo2.entity.User;
import com.example.demo2.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // ✅ 1. Register a new user
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User savedUser = blogService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // ✅ 2. Get a user by ID
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.getUserById(id));
    }

    // ✅ 3. Create a new post
    @PostMapping("/post/{authorId}")
    public ResponseEntity<Post> createPost(
            @PathVariable Long authorId,
            @RequestBody Post post,
            @RequestParam(required = false) Set<String> tags) {
        Post createdPost = blogService.createPost(authorId, post, tags);
        return ResponseEntity.ok(createdPost);
    }

    // ✅ 4. Get a post by ID
    @GetMapping("/post/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.getPostById(id));
    }

    // ✅ 5. Search posts by title or content
    @GetMapping("/post/search")
    public ResponseEntity<List<Post>> searchPost(@RequestParam String query) {
        return ResponseEntity.ok(blogService.searchPost(query));
    }

    // ✅ 6. Get recent posts
    @GetMapping("/post/recent")
    public ResponseEntity<List<Post>> getRecentPosts(@RequestParam int days) {
        return ResponseEntity.ok(blogService.getRecentPosts(days));
    }

    // ✅ 7. Add a comment to a post
    @PostMapping("/comment")
    public ResponseEntity<Comment> addComment(
            @RequestParam Long postId,
            @RequestParam Long authorId,
            @RequestParam String content) {
        return ResponseEntity.ok(blogService.addComment(postId, authorId, content));
    }

    // ✅ 8. Get comments for a post
    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Long postId) {
        return ResponseEntity.ok(blogService.getCommentsByPost(postId));
    }
}
