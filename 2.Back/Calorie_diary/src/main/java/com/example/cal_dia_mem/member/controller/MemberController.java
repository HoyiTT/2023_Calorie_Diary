package com.example.cal_dia_mem.member.controller;

import ch.qos.logback.core.model.Model;
import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.cal_dia_mem.member.dto.MemberDTO;
import com.example.cal_dia_mem.member.service.MemberService;
import com.example.cal_dia_mem.profile.dto.ProfileDTO;
import com.example.cal_dia_mem.profile.service.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ProfileService profileService;

    //회원가입 페이지 출력
    @GetMapping("/member/save")
    public String saveForm(){
        return "/member/createaccount";
    }

    //memberService에 memberDTO넘겨줌
    @PostMapping("/member/save")
    public String save(@Valid MemberDTO memberDTO, Errors errors, org.springframework.ui.Model model) {
        System.out.println("memberDTO= " + memberDTO);

        if(errors.hasErrors()){
            //회원가입 실패 시 입력값 유지
            model.addAttribute("memberDTO",memberDTO);

            //유효성 통과 못한 필드와 메시지 핸들링
            Map<String, String> validatorResult = MemberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "/member/createaccount";
        }

        // site_user테이블에서 profile 테이블에 저장 할 값 복사
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setMemberEmail(memberDTO.getMemberEmail());
        profileDTO.setMemberName(memberDTO.getMemberName());

        System.out.println("profileDTO= "+ profileDTO);
        memberService.save(memberDTO);
        profileService.save(profileDTO);

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
            // 세션 사용 - 회원별 데이터 식별 시 사용됨
            HttpSession session =request.getSession();
            session.setAttribute("sessionNickname",loginResult.getMemberNickname());
            session.setAttribute("sessionEmail",loginResult.getMemberEmail());
            return "index";
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
    // 세션 반환 후 로그아웃
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        return "/member/login";
    }

    @PostMapping("/member/email-check")
    //이메일 중복체크
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail){
        System.out.println("memberEmail ="+ memberEmail);
        String checkResult =memberService.emailCheck(memberEmail);
        return checkResult;
    }


    @PostMapping("/member/nickname-check")
    //닉네임 중복 체크
    public @ResponseBody String nickNameCheck(@RequestParam("memberNickname") String memberNickname){
        System.out.println("memberNickname ="+ memberNickname);
        String checkResult =memberService.NicknameCheck(memberNickname);
        return checkResult;
    }
}

