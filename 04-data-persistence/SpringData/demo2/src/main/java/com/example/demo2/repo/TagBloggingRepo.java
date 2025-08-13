package com.example.demo2.repo;

import com.example.demo2.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TagBloggingRepo extends JpaRepository<Tag,Long> {
    Optional<Tag> findByName(String name);

    List<Tag> findByNameIn(Collection<String> names);
}
