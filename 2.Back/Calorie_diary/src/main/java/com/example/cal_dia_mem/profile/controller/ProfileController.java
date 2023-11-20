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
        double bminum = 0;
        if(profileDTO.getCurrentWeight()!=null&&profileDTO.getHeight()!=null) {
            bminum = Double.parseDouble(profileDTO.getCurrentWeight()) / (Double.parseDouble(profileDTO.getHeight()) / 100)
                    / (Double.parseDouble(profileDTO.getHeight()) / 100);
        }
        String bmi = String.format("%.1f", bminum);

        //프로필 입력이 다 되어 있다면?
        if(profileDTO.getPurposeBodyFat()!=null&&profileDTO.getPurposeMuscle()!=null&&profileDTO.getPurposeBMI()!=null
                &&profileDTO.getHeight()!=null&&profileDTO.getCurrentWeight()!=null&&profileDTO.getBodyFat()!=null
                &&profileDTO.getPurposeWeight()!=null&&profileDTO.getMuscle()!=null&&profileDTO.getGender()!=null)
        {
            //목표 골격근
            Integer percentMuscle=Integer.parseInt(profileDTO.getMuscle())*100/Integer.parseInt(profileDTO.getPurposeMuscle());

            //목표 bmi
            double percentBmi=Double.parseDouble(profileDTO.getCurrentWeight())/(Double.parseDouble(profileDTO.getHeight())/100)
                    /(Double.parseDouble(profileDTO.getHeight())/100)*100/Double.parseDouble(profileDTO.getPurposeBMI());
            Integer percentBMI=(int)percentBmi;
            //목표 체지방
            Integer percentBodyFat=Integer.parseInt(profileDTO.getPurposeBodyFat())*100/Integer.parseInt(profileDTO.getBodyFat());

            Integer percentWeight = Integer.parseInt(profileDTO.getCurrentWeight())*100/Integer.parseInt(profileDTO.getPurposeWeight());

            profileDTO.setPurposeWeight(String.valueOf(percentWeight));
            profileDTO.setPurposeBMI(String.valueOf(percentBMI));
            profileDTO.setPurposeMuscle(String.valueOf(percentMuscle));
            profileDTO.setPurposeBodyFat(String.valueOf(percentBodyFat));
        }

        if(profileDTO.getPurposeBMI()==null)profileDTO.setPurposeBMI("프로필을 입력해주세요.");
        if(profileDTO.getPurposeMuscle()==null)profileDTO.setPurposeMuscle("프로필을 입력해주세요.");
        if(profileDTO.getPurposeBodyFat()==null)profileDTO.setPurposeBodyFat("프로필을 입력해주세요.");
        if(profileDTO.getPurposeWeight()==null)profileDTO.setPurposeWeight("프로필을 입력해주세요.");

        model.addAttribute("modifyProfile",profileDTO);
        model.addAttribute("bmi",bmi);
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
