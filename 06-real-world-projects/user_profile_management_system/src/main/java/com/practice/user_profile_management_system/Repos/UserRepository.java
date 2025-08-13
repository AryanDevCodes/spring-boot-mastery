package com.practice.user_profile_management_system.Repos;

import com.practice.user_profile_management_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}