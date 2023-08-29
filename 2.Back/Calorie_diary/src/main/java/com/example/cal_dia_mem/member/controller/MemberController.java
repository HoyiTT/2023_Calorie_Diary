package com.example.cal_dia_mem.member.controller;

import ch.qos.logback.core.model.Model;
import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.cal_dia_mem.member.dto.MemberDTO;
import com.example.cal_dia_mem.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    //회원가입 페이지 출력
    @GetMapping("/member/save")
    public String saveForm(){
        return "/member/createaccount";
    }

    //memberService에 memberDTO넘겨줌
    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("memberDTO= " + memberDTO);
        memberService.save(memberDTO);
        return "/member/login";
    }

    @GetMapping("/member/login")
    public String loginForm(){
        return "/member/login";
    }
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO , HttpServletRequest request, Model model){
        MemberDTO loginResult = memberService.login(memberDTO);
        //로그인 성공
        if(loginResult != null){
            HttpSession session =request.getSession();
            session.setAttribute("sessionName",loginResult.getMemberName());
            session.setAttribute("sessionEmail",loginResult.getMemberEmail());
            return "main";
        }
        //로그인 실패
        else{
            // 알림창 및 리다이렉션
            request.setAttribute("message","아이디와 비밀번호가 일치하지 않습니다!");
            request.setAttribute("searchUrl","/member/login");
            return "/member/message";
        }
    }

    @GetMapping("/member/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        return "index";
    }

    @PostMapping("/member/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail){
        System.out.println("memberEmail ="+ memberEmail);
        String checkResult =memberService.emailCheck(memberEmail);
        return checkResult;
    }

    @PostMapping("/member/nickname-check")
    public @ResponseBody String nickNameCheck(@RequestParam("memberNickname") String memberNickname){
        System.out.println("memberNickname ="+ memberNickname);
        String checkResult =memberService.NicknameCheck(memberNickname);
        return checkResult;
    }
}

