package com.example.cal_dia_mem.profile.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {
    @GetMapping("/profile/modify")
    public String memberProfile(){
        return "/profile/profile";
    }
   // @PostMapping("/profile/modify")
   // public String profileModify(@Valid){
    //    return "index";
   // }
}
