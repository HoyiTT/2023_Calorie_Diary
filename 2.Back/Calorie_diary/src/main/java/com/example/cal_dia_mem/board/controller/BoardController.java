package com.example.cal_dia_mem.board.controller;

import com.example.cal_dia_mem.board.dto.BoardDTO;
import com.example.cal_dia_mem.board.entity.BoardEntity;
import com.example.cal_dia_mem.board.repository.BoardRepository;
import com.example.cal_dia_mem.board.service.BoardService;
import com.example.cal_dia_mem.member.dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/list")
    public String boardList(Model model){

        model.addAttribute("list", boardService.boardList());

        return "board";
    }

    @GetMapping("/board/write")
    public String boardWriteForm()
    {
        return "/board/boardWrite";
    }
    @PostMapping("/board/writepro")
    public String boardWritePro(BoardDTO boardDTO, Model model){
        System.out.println(boardDTO.getTitle()+boardDTO.getContent());
        boardService.Write(boardDTO);
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "/member/message";
    }


    @GetMapping("/board/view")  // localhost:8081/board/view?id=1
    public String boardView(Model model, Integer id){

        model.addAttribute("board",boardService.boardView(id));
        return "view";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id){
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model){
        model.addAttribute("board",boardService.boardView(id));
        return "boardModify";
    }
    private BoardRepository boardRepository;
    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, BoardEntity boardEntity) {

        BoardEntity boardTemp=boardService.boardView(id);
        

        boardTemp.setTitle(boardTemp.getTitle());
        boardTemp.setContent(boardTemp.getContent());

        boardRepository.save(boardTemp);


        return "redirect:/board/list";

    }
}
