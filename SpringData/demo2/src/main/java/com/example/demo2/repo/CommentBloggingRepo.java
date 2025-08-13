package com.example.demo2.repo;

import com.example.demo2.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface CommentBloggingRepo extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(Long postId);
    List<Comment> findByAuthorId(Long authorId);
    List<Comment> findByPostIdOrderByCreatedAtDesc(Long postId);

    @Query("select  c from  Comment c where c.post.id = :postId and c.createdAt > :date")
    List<Comment> findRecentCommentsByPostId(@Param("postId") Long postId, @Param("date") LocalDate date);


}
