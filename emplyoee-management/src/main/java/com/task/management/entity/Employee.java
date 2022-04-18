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
    @Column(name = "employee_id")
    private int employeeId;
    @Column(name = "full_name", columnDefinition = "VARCHAR(250)")
    private String fullName;

    @Column(name = "address", columnDefinition = "VARCHAR(250)")
    private String address;


    @Column(name = "email", columnDefinition = "VARCHAR(250)")
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "salary")
    private double salary;


    @Column(name = "experience")
    private int experience;

    @Column(name = "bonus")
    private double bonus;

    @Column(name = "bonus_distributed_date")
    @Temporal(TemporalType.DATE)
    private Date bonusDistributedDate;


    @Transient
    private String logType;


    @Transient
    private String errorDescription;


}
