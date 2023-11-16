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
        model.addAttribute("modifyProfile",profileDTO);
        return "/profile/profile";

    }

    @PostMapping("/profile/modify")
    public String profileUpdate(@ModelAttribute ProfileDTO profileDTO, HttpServletRequest request){

        profileService.save(profileDTO);
        request.setAttribute("message","회원 정보 수정이 완료되었습니다.");
        request.setAttribute("searchUrl","/index");
        return "/member/message";
    }
}
