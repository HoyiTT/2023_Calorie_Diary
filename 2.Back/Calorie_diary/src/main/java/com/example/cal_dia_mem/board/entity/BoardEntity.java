package com.example.cal_dia_mem.board.entity;

import com.example.cal_dia_mem.board.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.SimpleNaturalIdLoadAccess;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity
@Setter
@Getter
@Table(name="Board_table")
@Data
public class  BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String memberEmail;
    private String memberNickname;
    private Integer view;
    @CreationTimestamp
    private Timestamp create_date;

    public String getCreateDate(){
        return new SimpleDateFormat("yyyy.MM.dd").format(create_date);
    }

    public static BoardEntity toBoardEntity(BoardDTO boardDTO){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContent(boardDTO.getContent());
        boardEntity.setMemberEmail(boardDTO.getMemberEmail());
        boardEntity.setMemberNickname(boardDTO.getMemberNickname());
        boardEntity.setView(boardDTO.getView());
        return boardEntity;
    }
}


