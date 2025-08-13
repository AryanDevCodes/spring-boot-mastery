package org.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstApp {
    @GetMapping("hello")
    public String displayMessage(){
    return "index.html";
    }

}
