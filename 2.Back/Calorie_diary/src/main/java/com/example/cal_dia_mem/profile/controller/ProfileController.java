package com.example.cal_dia_mem.profile.controller;

import com.example.cal_dia_mem.board.entity.BoardEntity;
import com.example.cal_dia_mem.profile.dto.ProfileDTO;
import com.example.cal_dia_mem.profile.entity.ProfileEntity;
import com.example.cal_dia_mem.profile.service.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    @Autowired
    private ProfileService profileService;


    @GetMapping("/profile/modify")

    // 프로필 수정 시 사용자 기존 데이터 출력
    public String memberProfile(HttpSession session, Model model){
        String myEmail = (String) session.getAttribute("sessionEmail");

        ProfileDTO profileDTO = profileService.modifyProfile(myEmail);
        double bminum = 1;
        if(!profileDTO.getCurrentWeight().equals("")&&!profileDTO.getHeight().equals("")) {
            bminum = Double.parseDouble(profileDTO.getCurrentWeight()) / (Double.parseDouble(profileDTO.getHeight()) / 100)
                    / (Double.parseDouble(profileDTO.getHeight()) / 100);
        }
        String bmi = String.format("%.1f", bminum);


        if (!profileDTO.getMuscle().equals("") && !profileDTO.getPurposeMuscle().equals("")) {
            Integer percentMuscle = Integer.parseInt(profileDTO.getMuscle()) * 100 / Integer.parseInt(profileDTO.getPurposeMuscle());
            profileDTO.setPurposeMuscle(String.valueOf(percentMuscle));
        }

        //목표 bmi
        if (!profileDTO.getCurrentWeight().equals("") && !profileDTO.getHeight().equals("") && !profileDTO.getPurposeBMI().equals("")) {

            double percentBmi = Double.parseDouble(profileDTO.getCurrentWeight()) / (Double.parseDouble(profileDTO.getHeight()) / 100)
                    / (Double.parseDouble(profileDTO.getHeight()) / 100) * 100 / Double.parseDouble(profileDTO.getPurposeBMI());
            Integer percentBMI = (int) percentBmi;
            profileDTO.setPurposeBMI(String.valueOf(percentBMI));
        }

        //목표 체지방
        if (!profileDTO.getPurposeBodyFat().equals("") && !profileDTO.getBodyFat().equals("")) {

            Integer percentBodyFat = Integer.parseInt(profileDTO.getPurposeBodyFat()) * 100 / Integer.parseInt(profileDTO.getBodyFat());
            profileDTO.setPurposeBodyFat(String.valueOf(percentBodyFat));
        }

        //목표 체중
        if (!profileDTO.getCurrentWeight().equals("") && !profileDTO.getPurposeWeight().equals("")) {
            Integer percentWeight = Integer.parseInt(profileDTO.getCurrentWeight()) * 100 / Integer.parseInt(profileDTO.getPurposeWeight());
            profileDTO.setPurposeWeight(String.valueOf(percentWeight));
        }

        model.addAttribute("modifyProfile",profileDTO);
        model.addAttribute("bmi",bmi);
        return "/profile/profile";

    }

    @PostMapping("/profile/modify")
    public String profileUpdate(@ModelAttribute ProfileDTO profileDTO, HttpServletRequest request){

        profileService.save(profileDTO);
        request.setAttribute("message","회원 정보 수정이 완료되었습니다.");
        request.setAttribute("searchUrl","/index/call");
        return "/member/message";
    }
}
