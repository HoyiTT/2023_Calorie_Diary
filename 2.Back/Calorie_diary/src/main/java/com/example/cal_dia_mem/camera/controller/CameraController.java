package com.example.cal_dia_mem.camera.controller;

import com.example.cal_dia_mem.camera.entity.CameraEntity;
import com.example.cal_dia_mem.camera.repository.CameraRepository;
import com.example.cal_dia_mem.camera.service.CameraService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class   CameraController {

    private CameraRepository cameraRepository;
    private CameraService cameraService;


    @GetMapping("/camera")
    public String Camera(CameraEntity cameraEntity, Model model, MultipartFile file) throws Exception {

        cameraService.Camera(cameraEntity,file);

        return "/camera/camera";
    }

}

