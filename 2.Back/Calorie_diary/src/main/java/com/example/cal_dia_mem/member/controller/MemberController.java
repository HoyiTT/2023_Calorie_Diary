package com.example.cal_dia_mem.member.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.cal_dia_mem.board.dto.BoardDTO;
import com.example.cal_dia_mem.board.service.BoardService;
import com.example.cal_dia_mem.diary.dto.DiaryDTO;
import com.example.cal_dia_mem.diary.repository.DiaryRepository;
import com.example.cal_dia_mem.diary.service.DiaryService;
import com.example.cal_dia_mem.foodCommend.dto.FoodCommendDTO;
import com.example.cal_dia_mem.foodCommend.service.FoodCommendService;
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
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ProfileService profileService;

    private final DiaryService diaryService;

    private final BoardService boardService;

    private final FoodCommendService foodCommendService;
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
            Date todayDate = new Date(System.currentTimeMillis());
            // 오늘 날짜와 멤버 이메일을 매개변수로 회원 별 오늘 섭취한 음식 및 영양성분 받아오기
            List<DiaryDTO> dto=diaryService.callDiary(todayDate,loginResult.getMemberEmail());
            model.addAttribute("todayList",dto);

            // 오늘 날짜와 멤버 이메일을 매개변수로 회원 별 오늘 초과한 영양성분 받아오기
            List<String> overNutrient = diaryService.overNutrient(todayDate,loginResult.getMemberEmail());
            if(overNutrient==null){
                model.addAttribute("overNutrient","정확한 서비스를 제공 위해 프로필설정을 해주세요.");
            }
            else {
                model.addAttribute("overNutrient", overNutrient);
            }
            // 오늘 날짜와 멤버 이메일을 매개변수로 회원 별 오늘 부족한 영양성분 받아오기
            List<String> scarceNutrient = diaryService.scarceNutrient(todayDate,loginResult.getMemberEmail());
            if(scarceNutrient==null){
                model.addAttribute("scarceNutrient","정확한 서비스를 제공 위해 프로필설정을 해주세요.");
            }
            else{
                model.addAttribute("scarceNutrient", scarceNutrient);
            }
            //최근 3일동안 가장 인기있는 게시글 5개 가져오기
            List<BoardDTO> poplarBoard = boardService.popularBoard();
            model.addAttribute("poplarBoard",poplarBoard);

            // 음식추천
            List<FoodCommendDTO> foodCommendDTOList=foodCommendService.commendFood();

            model.addAttribute("commendNutrient",foodCommendDTOList);

            String commendInfo=foodCommendService.foodCommendInfo(foodCommendDTOList);
            model.addAttribute("commendInfo",commendInfo);

            List<String> meal = new ArrayList<>();
            meal.add("아침"); meal.add("점심"); meal.add("저녁");
            model.addAttribute("meal",meal);
            return "index";
        }
        //로그인 실패
        else{
            // 알림창 및 리다이렉션
            model.addAttribute("message","아이디와 비밀번호가 일치하지 않습니다!");
            model.addAttribute("searchUrl","/member/login");
            return "/member/message";
        }
    }

    @GetMapping("/index/call")
    public String index(HttpServletRequest request, Model model,HttpSession session){
        String myEmail = (String) session.getAttribute("sessionEmail");
        Date todayDate = new Date(System.currentTimeMillis());
        // 오늘 날짜와 멤버 이메일을 매개변수로 회원 별 오늘 섭취한 음식 및 영양성분 받아오기
        List<DiaryDTO> dto=diaryService.callDiary(todayDate,myEmail);
        model.addAttribute("todayList",dto);

        // 오늘 날짜와 멤버 이메일을 매개변수로 회원 별 오늘 초과한 영양성분 받아오기
        List<String> overNutrient = diaryService.overNutrient(todayDate,myEmail);
        model.addAttribute("overNutrient",overNutrient);

        // 오늘 날짜와 멤버 이메일을 매개변수로 회원 별 오늘 부족한 영양성분 받아오기
        List<String> scarceNutrient = diaryService.scarceNutrient(todayDate,myEmail);
        model.addAttribute("scarceNutrient",scarceNutrient);

        //최근 3일동안 가장 인기있는 게시글 5개 가져오기
        List<BoardDTO> poplarBoard = boardService.popularBoard();
        model.addAttribute("poplarBoard",poplarBoard);
        return "index";
    }

    @PostMapping("/index/call/past")
    public String index(Model model,HttpSession session,@RequestParam("pastDate") String Date) {
        String myEmail = (String) session.getAttribute("sessionEmail");
        String dateString = Date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = null;
        try {
            utilDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (utilDate != null) {
            Date pastDate = new Date(utilDate.getTime());


            List<DiaryDTO> dto = diaryService.callDiary(pastDate, myEmail);
            model.addAttribute("todayList", dto);

            // 과거 날짜와 멤버 이메일을 매개변수로 회원 별 오늘 초과한 영양성분 받아오기
            List<String> overNutrient = diaryService.overNutrient(pastDate, myEmail);
            model.addAttribute("overNutrient", overNutrient);

            // 과거 날짜와 멤버 이메일을 매개변수로 회원 별 오늘 부족한 영양성분 받아오기
            List<String> scarceNutrient = diaryService.scarceNutrient(pastDate, myEmail);
            model.addAttribute("scarceNutrient", scarceNutrient);

            //오늘 3일동안 가장 인기있는 게시글 5개 가져오기
            List<BoardDTO> poplarBoard = boardService.popularBoard();
            model.addAttribute("poplarBoard", poplarBoard);
        }
        return "index";
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

