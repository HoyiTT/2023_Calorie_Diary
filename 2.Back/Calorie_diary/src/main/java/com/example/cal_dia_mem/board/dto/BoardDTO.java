package com.example.cal_dia_mem.board.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDTO {
    private Integer id;
    private String title;
    private String content;
    private String memberEmail;
    private String memberNickname;
    private Integer view;
    private Timestamp create_date;
}
