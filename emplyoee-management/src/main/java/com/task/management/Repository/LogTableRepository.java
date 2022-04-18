package com.task.management.Repository;

import com.task.management.entity.LogTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogTableRepository extends JpaRepository<LogTable,Long> {
}
