package com.example.cal_dia_mem.camera.controller;


import com.example.cal_dia_mem.camera.dto.OcrDTO;
import com.example.cal_dia_mem.camera.service.CameraService;
import com.example.cal_dia_mem.junFood.dto.JunFoodDTO;
import com.example.cal_dia_mem.junFood.service.JunFoodService;
import jakarta.persistence.Access;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
public class CameraController {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/food/";
    CameraService cameraService = new CameraService();
    @Autowired
    JunFoodService junFoodService;

    @RequestMapping("/camera")
    public String showUploadForm(Model model,HttpSession session) {
        String myEmail=(String)session.getAttribute("sessionEmail");
        JunFoodDTO junFoodDTO =new JunFoodDTO();
        JunFoodDTO junFoodDTO1 = new JunFoodDTO();
        junFoodDTO.setFoodName(" ");
        junFoodDTO.setKcal(" ");
        junFoodDTO.setCarbohydrate(" ");
        junFoodDTO.setProtein(" ");
        junFoodDTO.setFat(" ");
        junFoodDTO.setSugars(" ");
        junFoodDTO.setSalt(" ");
        junFoodDTO.setCholesterol(" ");
        junFoodDTO.setSaturated_fatty(" ");
        junFoodDTO.setTransfat(" ");

        junFoodDTO1= (JunFoodDTO) session.getAttribute("junFood");
        if(junFoodDTO1!=null){
            junFoodDTO=junFoodDTO1;
        }
        model.addAttribute("memberEmail",myEmail);
        model.addAttribute("junFood",junFoodDTO);
        System.out.println(junFoodDTO);

        return "/camera/camera";
    }



    @PostMapping("/camera/upload")
    public String handleFileUpload(@RequestParam("choice") String choice,@RequestParam("fileUpload") MultipartFile file, RedirectAttributes redirectAttributes, Model model, HttpSession session) {
        String myEmail=(String)session.getAttribute("sessionEmail");
        System.out.println(choice);
        if (choice.equals("nutrient")) { //영양성분을 선택했을때 영양성분 관련 기능들을 실행
            if (file.isEmpty()) {
                model.addAttribute("message", "Please select a file to upload.");
                model.addAttribute("searchUrl", "/camera");
                return "/member/message";
            }
            try {
                String fileName = myEmail + "_nutrient.jpg";
                String filePath = UPLOAD_DIR + fileName;

                File dest = new File(filePath);
                file.transferTo(dest);

                model.addAttribute("message", "File uploaded successfully: " + fileName);
                model.addAttribute("searchUrl", "/camera");

                List<OcrDTO> nutrientlist = cameraService.ocr(myEmail); //ocr을 통해 뽑아온 영양성분을 저장하는 곳
                System.out.println(nutrientlist);

                return "/member/message";
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "File upload failed.");
                model.addAttribute("searchUrl", "/camera");
                return "/member/message";
            }
        } else if(choice.equals("food")) {  //음식을 선택했을때 음식 이름관련 기능들을 실행
            if (file.isEmpty()) {
                model.addAttribute("message", "Please select a file to upload.");
                model.addAttribute("searchUrl", "/camera");
                return "/member/message";
            }
            try {
                String fileName = myEmail + "_food.jpg";
                String filePath = UPLOAD_DIR + fileName;


                File dest = new File(filePath);
                file.transferTo(dest);

                String classs = cameraService.ApiService(myEmail); // ApiService를 이용해 업로드한 사진의 음식 이름 클래스를 받아옴
                int newResult=Integer.parseInt(classs);
                newResult=newResult+1;
                Integer result=newResult;
                JunFoodDTO junFoodDTO;
                junFoodDTO=junFoodService.flushJunFood(result);
                session.setAttribute("junFood",junFoodDTO);

                model.addAttribute("message", "File uploaded successfully: " + fileName);
                model.addAttribute("searchUrl", "/camera");

                return "/member/message";
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "File upload failed.");
                model.addAttribute("searchUrl", "/camera");
                return "/member/message";
            }
        } else {
            model.addAttribute("message", "음식인지 영양성분인지 선택해주세요");
            model.addAttribute("searchUrl", "/camera");
            return "/member/message";
        }
    }
}