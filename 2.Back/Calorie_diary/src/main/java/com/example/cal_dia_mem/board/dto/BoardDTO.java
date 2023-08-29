package com.example.cal_dia_mem.board.dto;

import lombok.*;

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
}
