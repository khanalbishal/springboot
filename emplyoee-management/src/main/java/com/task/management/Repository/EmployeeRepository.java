package com.task.management.Repository;

import com.task.management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "select * from tbl_employee where experince=2 ", nativeQuery = true)
    List<Employee> getTwoYearsOfExperince();
    @Query(value = "select * from tbl_employee where experince>2 and experince<4 ", nativeQuery = true)
    List<Employee> getExperinceBetween2And4();
    @Query(value = "select * from tbl_employee where experince>4 and experince<6 ", nativeQuery = true)
    List<Employee> getExperinceBetween4And6();
    @Query(value = "select * from tbl_employee where experince>6", nativeQuery = true)
    List<Employee> getExperinceGreaterThan6();


    @Query(value = "UPDATE tbl_employee\n" +
            "set salary=?1,\n" +
            "    bonus_distributed_date=?2,\n" +
            "    bonus=?4\n" +
            "\n" +
            "where id=?3", nativeQuery = true)
    void updateSalary(double salaryWithBonus, Date date, Long id, double bonus);
}
