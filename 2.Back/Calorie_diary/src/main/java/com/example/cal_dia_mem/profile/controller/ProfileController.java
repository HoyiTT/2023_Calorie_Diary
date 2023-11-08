package com.example.cal_dia_mem.profile.controller;

import com.example.cal_dia_mem.board.entity.BoardEntity;
import com.example.cal_dia_mem.profile.dto.ProfileDTO;
import com.example.cal_dia_mem.profile.entity.ProfileEntity;
import com.example.cal_dia_mem.profile.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

    private ProfileService profileService;
    @GetMapping("/profile/modify")
    public String memberProfile(){
        return "/profile/profile";

    }

    @PostMapping("/profile/modify/{memberEmail}")
    public String profileUpdate(@PathVariable("memberEmail")String memberEmail, ProfileDTO profileDTO){

     /*   ProfileDTO profileTemp=profileService.modify(memberEmail);

        profileTemp.setGender(profileDTO.getGender());
        profileTemp.setHeight(profileDTO.getHeight());
        profileTemp.setCurrentWeight(profileDTO.getCurrentWeight());
        profileTemp.setPurposeWeight(profileDTO.getPurposeWeight());
        profileTemp.setPurposeBMI(profileDTO.getPurposeBMI());
        profileTemp.setMuscle(profileDTO.getMuscle());
        profileTemp.setBodyFat(profileDTO.getBodyFat());

        profileService.save(profileTemp);   */
        return "index";
    }
}
