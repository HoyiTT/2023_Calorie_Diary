package com.example.cal_dia_mem.camera.dto;


import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CameraDTO {
    private String memberEmail;
    private String memberNickname;
    private String filename;
    private String filepath;
}
