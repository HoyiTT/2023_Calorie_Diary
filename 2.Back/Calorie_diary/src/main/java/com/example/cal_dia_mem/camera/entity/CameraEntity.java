package com.example.cal_dia_mem.camera.entity;


import com.example.cal_dia_mem.camera.dto.CameraDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="Camera_table")
@Data
public class  CameraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String memberEmail;
    private String memberNickname;
    private String filename;
    private String filepath;


    public static com.example.cal_dia_mem.camera.entity.CameraEntity toCameraEntity(CameraDTO cameraDTO){
        CameraEntity cameraEntity = new CameraEntity();
        cameraEntity.setMemberEmail(cameraDTO.getMemberEmail());
        cameraEntity.setMemberNickname(cameraDTO.getMemberNickname());
        cameraEntity.setFilename(cameraDTO.getFilename());
        cameraEntity.setFilepath(cameraDTO.getFilepath());

        return cameraEntity;
    }
}


