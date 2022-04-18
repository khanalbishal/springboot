package com.task.management.service;

import com.task.management.entity.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface FileReadAndWriteService {
    void readAndWrite(MultipartFile[] uploadFile);

    List<Employee> getAllEmolyoeeData();
}
