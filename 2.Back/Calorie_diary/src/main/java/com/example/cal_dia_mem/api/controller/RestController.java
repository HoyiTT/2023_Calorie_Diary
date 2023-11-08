package com.example.cal_dia_mem.api.controller;

import com.example.cal_dia_mem.api.Service.ApiService;
import com.example.cal_dia_mem.api.dto.ApiDTO;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class   RestController {
    @Autowired
    ApiService apiService;
    @GetMapping("/api")
    public String callApi(Model model) throws ParseException {
        String jsonStr;
        jsonStr= apiService.callApi("아이스크림");
        List<ApiDTO> datalist = apiService.jsonParse(jsonStr);
        model.addAttribute("datalist",datalist);
        System.out.println(datalist);
        return jsonStr;
    }

}

