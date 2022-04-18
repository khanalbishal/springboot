package com.task.management.service;

import com.task.management.Repository.LogTableRepository;
import com.task.management.entity.LogTable;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class LogTypeServiceImpl implements LogTypeService {
    private final LogTableRepository logTableRepository;

    public LogTypeServiceImpl(LogTableRepository logTableRepository) {
        this.logTableRepository = logTableRepository;
    }

    @Override
    public List<LogTable> findAllData() {
        return logTableRepository.findAll();
    }
}
