package org.practice.bootpro8multiprofiles.Service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.practice.bootpro8multiprofiles.model.UserDetails;
import org.practice.bootpro8multiprofiles.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Transactional
    public UserDetails createUser(UserDetails user){
        user.setProfile(activeProfile);
        return userRepository.save(user);
    }

    public List<UserDetails> getAllUsers(){
        return userRepository.findAll();
    }
    public List<UserDetails> getUserByProfile(){
        return userRepository.findByProfile(activeProfile);
    }

    public UserDetails getUserByUsername(String username){
        return userRepository.findByUserName(username);
    }
}
