package com.example.demo2.service;

import com.example.demo2.entity.Comment;
import com.example.demo2.entity.Post;
import com.example.demo2.entity.Tag;
import com.example.demo2.entity.User;
import com.example.demo2.repo.CommentBloggingRepo;
import com.example.demo2.repo.PostBloggingRepo;
import com.example.demo2.repo.TagBloggingRepo;
import com.example.demo2.repo.UserBloggingRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class BlogService {
    private UserBloggingRepo userBloggingRepo;
    private PostBloggingRepo postBloggingRepo;
    private CommentBloggingRepo commentBloggingRepo;
    private TagBloggingRepo tagBloggingRepo;

    public BlogService(UserBloggingRepo userBloggingRepo, PostBloggingRepo postBloggingRepo, CommentBloggingRepo commentBloggingRepo, TagBloggingRepo tagBloggingRepo) {
        this.userBloggingRepo = userBloggingRepo;
        this.postBloggingRepo = postBloggingRepo;
        this.commentBloggingRepo = commentBloggingRepo;
        this.tagBloggingRepo = tagBloggingRepo;
    }

    public User registerUser(User user) {
        if (userBloggingRepo.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email address already in use"+user.getEmail());
        }
        return userBloggingRepo.save(user);
    }
    public User getUserById(Long id) {
        return userBloggingRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("User not found with ID "+id));
    }

    // Post operation

public Post createPost(Long authorId, Post post, Set<String> tagNames) {
        User author = getUserById(authorId);
        post.setAuthor(author);
        post.setCreatedAt(LocalDateTime.now());

        if (tagNames != null && !tagNames.isEmpty()) {
            Set<Tag> tags = new HashSet<>();

            for (String tagName : tagNames) {
                Tag tag = tagBloggingRepo.findByName(tagName).orElseGet(()-> {
                    Tag tag2 = new Tag();
                    tag2.setName(tagName);
                    return tagBloggingRepo.save(tag2);
                });
                tags.add(tag);
            }
            post.setTags(tags);
        }
        return postBloggingRepo.save(post);
}
public Post getPostById(Long id) {
        return postBloggingRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("Post not found with ID "+id));
}

public List<Post> searchPost(String searchTerm){
        return postBloggingRepo.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
}

// Comment Operation
    public Comment addComment(Long postId, Long authorId, String content) {
        Post post = getPostById(postId);
        User author = getUserById(authorId);

        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setPost(post);
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());
        return commentBloggingRepo.save(comment);
    }

    public List<Comment> getCommentsByPost(Long postId) {
        return commentBloggingRepo.findByPostIdOrderByCreatedAtDesc(postId);
    }
    public List<Post> getRecentPosts(int days) {
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(days);
        return postBloggingRepo.findRecentPosts(LocalDate.from(localDateTime));
    }

    public List<Tag> getPopularTags() {
        return tagBloggingRepo.findAll();
    }
}
