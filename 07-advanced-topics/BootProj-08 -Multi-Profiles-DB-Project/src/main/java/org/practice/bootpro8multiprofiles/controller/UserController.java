package org.practice.bootpro8multiprofiles.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.practice.bootpro8multiprofiles.Service.UserService;
import org.practice.bootpro8multiprofiles.model.UserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @Value("${spring.profiles.active}")
    private  String activeProfile;

    @PostMapping
    public UserDetails createUser(@RequestBody UserDetails user) {
        return userService.createUser(user);
    }

    @GetMapping("/profile")
    public List<UserDetails> getUserByProfile(){
        return userService.getUserByProfile();
    }

    @GetMapping("/active-profile")
    public String getActiveProfile(){
        return "Active Profile : "+activeProfile;
    }

    @GetMapping("/{username}")
    public UserDetails getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }


}
