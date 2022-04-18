package com.task.management.service;

import com.task.management.entity.LogTable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface LogTypeService {

    List<LogTable> findAllData();
}
