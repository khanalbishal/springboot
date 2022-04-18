package com.task.management.entity;

import com.poiji.annotation.ExcelCellName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_employee_seq_gen")
    @SequenceGenerator(name = "tbl_employee_seq_gen", sequenceName = "seq_tbl_employee", initialValue = 1, allocationSize = 1)
    private Long id;
    @Column(name = "employee_id", nullable = true)
    private int employeeId;
    @Column(name = "full_name", columnDefinition = "VARCHAR(250)", nullable = true)
    private String fullName;

    @Column(name = "address", columnDefinition = "VARCHAR(250)", nullable = true)
    private String address;


    @Column(name = "email", columnDefinition = "VARCHAR(250)", nullable = true)
    private String email;

    @Column(name = "mobile_number", nullable = true)
    private String mobileNumber;

    @Column(name = "salary", nullable = true)
    private double salary;


    @Column(name = "experience", nullable = true)
    private int experience;

    @Column(name = "bonus", nullable = true)
    private double bonus;

    @Column(name = "bonus_distributed_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date bonusDistributedDate;


    @Transient
    private String logType;


    @Transient
    private String errorDescription;


}
