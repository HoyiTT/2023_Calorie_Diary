package com.example.cal_dia_mem.diary.controller;

import com.example.cal_dia_mem.api.dto.ApiDTO;
import com.example.cal_dia_mem.diary.dto.DiaryDTO;
import com.example.cal_dia_mem.diary.service.DiaryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;
    @PostMapping("/diary/save")
    public String savaData(@ModelAttribute DiaryDTO diaryDTO, HttpServletRequest request) {
        diaryService.save(diaryDTO);
        request.setAttribute("message","금일 다이어리에 저장되었습니다.");
        request.setAttribute("searchUrl","/diray");
        return "/member/message";
    }

    @GetMapping("/diary")
    public String diary(HttpServletRequest request, Model model, HttpSession session){
        String myEmail = (String) session.getAttribute("sessionEmail");
        Date todayDate = new Date(System.currentTimeMillis());
        // 오늘 날짜와 멤버 이메일을 매개변수로 회원 별 오늘 섭취한 음식 및 영양성분 받아오기
        List<DiaryDTO> dto=diaryService.callDiary(todayDate,myEmail);

        if (!dto.isEmpty()) {

            // 리스트의 마지막 음식 가져오기
            DiaryDTO lastFood = dto.get(dto.size() - 1);
            //String foodname = lastFood.getFood_name();

            // 모델에 마지막 음식 추가
            model.addAttribute("lastFood", lastFood);

            System.out.println("ㅇㄹㅇㄹㅇㄹㅇ"+lastFood);

        }
        return "/camera/diary";
    }


}
