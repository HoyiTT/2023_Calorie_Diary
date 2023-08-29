package com.example.cal_dia_mem.board.controller;

import com.example.cal_dia_mem.board.dto.BoardDTO;
import com.example.cal_dia_mem.board.service.BoardService;
import com.example.cal_dia_mem.member.dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;
    @GetMapping("/board")
    public String board() {
        return "/board/board";
    }

    @GetMapping("/board/write")
    public String boardWriteForm(){
        return "/board/boardWrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(@ModelAttribute BoardDTO boardDTO){
        System.out.println(boardDTO.getTitle()+boardDTO.getContent());
        boardService.Write(boardDTO);
        return "";
    }
}
