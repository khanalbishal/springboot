package com.task.management.scheduler;

import com.task.management.Repository.EmployeeRepository;
import com.task.management.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CalculatingBonus {

    private final EmployeeRepository employeeRepository;

    @Scheduled(cron = "*/300 * * * * ?")
    public void calculateBonusForEmployees() {


        List<Employee> employees = employeeRepository.getTwoYearsOfExperince();
        LocalDate currentDate = LocalDate.now();
        LocalDate currentDateMinus3Months = currentDate.minusMonths(3);
        employees.forEach(s -> {
            if (s.getBonusDistributedDate() != null) {
                LocalDate date = s.getBonusDistributedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (date.isBefore(currentDateMinus3Months)) {
                    Double bonus = s.getSalary() + 15 / 100 * s.getSalary();
                    Double salaryWithBonus = s.getSalary() + bonus;
                    employeeRepository.updateSalary(salaryWithBonus, new Date(), s.getId(), bonus);
                }
            }else {
                double bonus = s.getSalary() + 15 / 100 * s.getSalary();
                double salaryWithBonus = s.getSalary() + bonus;
                employeeRepository.updateSalary(salaryWithBonus, new Date(), s.getId(), bonus);
            }

        });
        List<Employee> employees1 = employeeRepository.getExperinceBetween2And4();
        employees1.forEach(s -> {
            if (s.getBonusDistributedDate() != null) {
                LocalDate date = s.getBonusDistributedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (date.isBefore(currentDateMinus3Months)) {
                    Double bonus = s.getSalary() + 15/ 100 * s.getSalary();
                    Double salaryWithBonus = s.getSalary() + bonus;
                    employeeRepository.updateSalary(salaryWithBonus, new Date(), s.getId(), bonus);
                }

            } else {
                double bonus = s.getSalary() + 15/ 100 * s.getSalary();
                double salaryWithBonus = s.getSalary() + bonus;
                employeeRepository.updateSalary(salaryWithBonus, new Date(), s.getId(), bonus);
            }

        });
        List<Employee> employees2 = employeeRepository.getExperinceBetween4And6();
        employees1.forEach(s -> {
            if (s.getBonusDistributedDate() != null) {
                LocalDate date = s.getBonusDistributedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (date.isBefore(currentDateMinus3Months)) {
                    Double bonus = s.getSalary() + 20/ 100 * s.getSalary();
                    Double salaryWithBonus = s.getSalary() + bonus;
                    employeeRepository.updateSalary(salaryWithBonus, new Date(), s.getId(), bonus);
                }

            } else {
                double bonus = s.getSalary() + 20/ 100 * s.getSalary();
                double salaryWithBonus = s.getSalary() + bonus;
                employeeRepository.updateSalary(salaryWithBonus, new Date(), s.getId(), bonus);
            }

        });
        List<Employee> employees3 = employeeRepository.getExperinceGreaterThan6();
        employees1.forEach(s -> {
            if (s.getBonusDistributedDate() != null) {
                LocalDate date = s.getBonusDistributedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (date.isBefore(currentDateMinus3Months)) {
                    Double bonus = s.getSalary() + 25/ 100 * s.getSalary();
                    Double salaryWithBonus = s.getSalary() + bonus;
                    employeeRepository.updateSalary(salaryWithBonus, new Date(), s.getId(), bonus);
                }

            } else {
                double bonus = s.getSalary() + 25/ 100 * s.getSalary();
                double salaryWithBonus = s.getSalary() + bonus;
                employeeRepository.updateSalary(salaryWithBonus, new Date(), s.getId(), bonus);
            }

        });


    }


}
