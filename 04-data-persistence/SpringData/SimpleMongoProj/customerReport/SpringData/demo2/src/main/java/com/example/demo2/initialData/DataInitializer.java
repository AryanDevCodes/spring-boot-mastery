package com.example.demo2.initialData;

import com.example.demo2.entity.Comment;
import com.example.demo2.entity.Post;
import com.example.demo2.entity.Tag;
import com.example.demo2.entity.User;
import com.example.demo2.repo.CommentBloggingRepo;
import com.example.demo2.repo.PostBloggingRepo;
import com.example.demo2.repo.TagBloggingRepo;
import com.example.demo2.repo.UserBloggingRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserBloggingRepo userBloggingRepo;
    private final PostBloggingRepo postBloggingRepo;
    private final CommentBloggingRepo commentBloggingRepo;
    private final TagBloggingRepo tagBloggingRepo;

    public DataInitializer(UserBloggingRepo userBloggingRepo, PostBloggingRepo postBloggingRepo,
                           CommentBloggingRepo commentBloggingRepo, TagBloggingRepo tagBloggingRepo) {
        this.userBloggingRepo = userBloggingRepo;
        this.postBloggingRepo = postBloggingRepo;
        this.commentBloggingRepo = commentBloggingRepo;
        this.tagBloggingRepo = tagBloggingRepo;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (userBloggingRepo.count() > 0) {
            return; // Avoid duplicate initialization
        }

        // Create Users
        User user1 = new User();
        user1.setName("Aryan Raj");
        user1.setEmail("aryan@example.com");
        user1.setPassword("password123"); // Consider hashing passwords
        user1 = userBloggingRepo.save(user1);

        User user2 = new User();
        user2.setName("John Doe");
        user2.setEmail("john@example.com");
        user2.setPassword("securepass");
        user2 = userBloggingRepo.save(user2);

        // Create Tags
        Tag techTag = new Tag();
        techTag.setName("Technology");
        techTag = tagBloggingRepo.save(techTag);

        Tag javaTag = new Tag();
        javaTag.setName("Java");
        javaTag = tagBloggingRepo.save(javaTag);

        // Create Posts
        Post post1 = new Post();
        post1.setTitle("Introduction to Spring Boot");
        post1.setContent("Spring Boot makes it easy to create stand-alone applications...");
        post1.setCreatedAt(LocalDateTime.now());
        post1.setAuthor(user1);
        post1.setTags(new HashSet<>(Set.of(techTag, javaTag)));
        post1 = postBloggingRepo.save(post1);

        Post post2 = new Post();
        post2.setTitle("Understanding Java Streams");
        post2.setContent("Java Streams allow functional-style processing of data...");
        post2.setCreatedAt(LocalDateTime.now());
        post2.setAuthor(user2);
        post2.setTags(new HashSet<>(Set.of(javaTag)));
        post2 = postBloggingRepo.save(post2);

        // Create Comments
        Comment comment1 = new Comment();
        comment1.setContent("Great article on Spring Boot!");
        comment1.setCreatedAt(LocalDateTime.now());
        comment1.setAuthor(user2);
        comment1.setPost(post1);
        commentBloggingRepo.save(comment1);

        Comment comment2 = new Comment();
        comment2.setContent("I love Java Streams, very helpful!");
        comment2.setCreatedAt(LocalDateTime.now());
        comment2.setAuthor(user1);
        comment2.setPost(post2);
        commentBloggingRepo.save(comment2);

        System.out.println("Initial data loaded successfully!");
    }
}
