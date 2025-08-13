package org.practice.simplefullstackproj_1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BootRestController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello From Spring Boot";
    }
}
