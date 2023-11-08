package com.example.cal_dia_mem.camera.controller;

import com.example.cal_dia_mem.camera.repository.CameraRepository;
import com.example.cal_dia_mem.camera.service.CameraService;
import com.example.cal_dia_mem.camera.dto.CameraDTO;
import com.example.cal_dia_mem.camera.entity.CameraEntity;
import jakarta.servlet.http.HttpSession;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Controller
public class   CameraController {

    private CameraRepository cameraRepository;
    private CameraService cameraService;


    @GetMapping("/camera")
    public String Camera(CameraEntity cameraEntity, Model model, MultipartFile file) throws Exception {

        cameraService.Camera(cameraEntity,file);

        return "camera";
    }

}

