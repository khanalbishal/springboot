package com.task.management.controller;

import com.task.management.entity.Employee;
import com.task.management.entity.LogTable;
import com.task.management.service.FileReadAndWriteService;
import com.task.management.service.LogTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/")
public class UploadController {

    private final FileReadAndWriteService fileReadAndWriteService;
    private final LogTypeService logTypeService;



    /*view for upload and employee Table and log table*/
    @GetMapping
    public String getAllData(Model model) {

        List<Employee> employeeList = fileReadAndWriteService.getAllEmolyoeeData();
        List<LogTable>logTableList=logTypeService.findAllData();
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("logTable", logTableList);
        return "main";
    }

    @PostMapping(value = "/upload")
    public String uploadFile(MultipartFile[] uploadFile, Model model) {

        fileReadAndWriteService.readAndWrite(uploadFile);


        return "main";
    }


}
