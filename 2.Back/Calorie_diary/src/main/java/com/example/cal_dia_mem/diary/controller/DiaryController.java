package com.example.cal_dia_mem.diary.controller;

import com.example.cal_dia_mem.api.dto.ApiDTO;
import com.example.cal_dia_mem.diary.dto.DiaryDTO;
import com.example.cal_dia_mem.diary.service.DiaryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;
    @PostMapping("/diary/save")
    public String savaData(@ModelAttribute DiaryDTO diaryDTO, HttpServletRequest request) {
        diaryService.save(diaryDTO);
        request.setAttribute("message","금일 다이어리에 추가되었습니다.");
        request.setAttribute("searchUrl","/api");
        return "/member/message";
    }
}
