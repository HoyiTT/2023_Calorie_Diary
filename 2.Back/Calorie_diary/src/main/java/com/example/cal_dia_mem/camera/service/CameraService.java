package com.example.cal_dia_mem.camera.service;

import com.example.cal_dia_mem.camera.entity.CameraEntity;
import com.example.cal_dia_mem.camera.repository.CameraRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class CameraService {

    private CameraRepository cameraRepository;
    public void Camera(CameraEntity cameraEntity, MultipartFile file) throws Exception {

        String projectPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, "name");

        file.transferTo(saveFile);

        cameraRepository.save(cameraEntity);


    }
}
