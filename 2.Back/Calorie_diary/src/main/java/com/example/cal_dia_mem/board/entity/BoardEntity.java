package com.example.cal_dia_mem.board.entity;

import com.example.cal_dia_mem.board.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="Board_table")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String memberEmail;
    private String memberNickname;

    public static BoardEntity toBoardEntity(BoardDTO boardDTO){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContent(boardDTO.getContent());
        boardEntity.setMemberEmail(boardDTO.getMemberEmail());
        boardEntity.setMemberNickname(boardDTO.getMemberNickname());
        return boardEntity;
    }
}


