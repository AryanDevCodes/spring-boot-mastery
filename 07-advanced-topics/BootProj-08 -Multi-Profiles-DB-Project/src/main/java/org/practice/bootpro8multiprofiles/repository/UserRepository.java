package org.practice.bootpro8multiprofiles.repository;

import org.practice.bootpro8multiprofiles.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {
    List<UserDetails> findByProfile(String profile);
    UserDetails findByUserName(String userName);
    UserDetails findByEmail(String email);
}