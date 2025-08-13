package com.example.demo2.repo;

import com.example.demo2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostBloggingRepo extends JpaRepository<Post,Long> {
    List<Post> findByAuthorId(Long author_id);
    List<Post> findByAuthorEmail(String email);
    List<Post> findByTitleContainingOrContentContaining(String title, String content);
    List<Post> findByCreatedAtAfter(LocalDate date);
    List<Post> findByTagsName(String tagName);

    @Query("select p from Post p where p.createdAt > :date order by p.createdAt desc ")
    List<Post> findRecentPosts(
            @Param("date") LocalDate date
    );


}
