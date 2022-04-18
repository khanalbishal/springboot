package com.task.management.service;


import com.task.management.LogTypeEnum.LogType;
import com.task.management.Repository.EmployeeRepository;
import com.task.management.Repository.LogTableRepository;
import com.task.management.dateconveterutill.DateConverterUtill;
import com.task.management.entity.Employee;
import com.task.management.entity.LogTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellAddress;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Component
@RequiredArgsConstructor
public class FileReadAndWriteServiceImpl implements FileReadAndWriteService {
    private final DateConverterUtill dateConverterUtill;
    private final LogTableRepository logTableRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public void readAndWrite(MultipartFile[] uploadFile) {
        NumberFormat formatter = new DecimalFormat("##.##");


        /*save uploaded file in static direcotry in local */

        /*get files path and files names from saved firecotry and insert to bewlo file input stream*/


        InputStream inp = null;
        try {
            inp = new FileInputStream("D:\\info\\emplyoee-management\\src\\main\\resources\\static\\uploads\\EmployeeDetails.xlsx");

            Workbook workbook = WorkbookFactory.create(inp);
            Iterator<Row> iterator = workbook.getSheetAt(0).iterator();

            List<Employee> employees = new ArrayList<>();
            while (iterator.hasNext()) {

                Employee employee = new Employee();

                Row currentRow = iterator.next();

                // don't read the header
                if (currentRow.getRowNum() == 0) {
                    continue;
                }

                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    CellAddress address = currentCell.getAddress();
                    try {
                        if (0 == address.getColumn()) {
                            employee.setEmployeeId(Integer.parseInt(currentCell.getStringCellValue()));
                        } else if (1 == address.getColumn()) {
                            employee.setFullName(currentCell.getStringCellValue());
                        } else if (2 == address.getColumn()) {
                            employee.setAddress(currentCell.getStringCellValue());
                        } else if (3 == address.getColumn()) {
                            employee.setEmail(currentCell.getStringCellValue());
                        } else if (4 == address.getColumn()) {
                            employee.setMobileNumber(formatter.format(currentCell.getNumericCellValue()));
                        } else if (5 == address.getColumn()) {
                            employee.setSalary(currentCell.getNumericCellValue());
                        } else if (6 == address.getColumn()) {
                            employee.setExperience((int) currentCell.getNumericCellValue());
                        } else if (7 == address.getColumn()) {
                            employee.setBonusDistributedDate(dateConverterUtill.convertExcelDate(currentCell.getDateCellValue()));
                        }
                    } catch (Exception e) {
                        employee.setLogType(LogType.EXCELREADERROR.name());
                        employee.setErrorDescription(e.getLocalizedMessage());
                    }

                }

                employees.add(employee);

            }
            /*
             * excel row data  With error
             * and save to log  table
             * */
            List<Employee> employeeList = employees.stream().filter(s -> s.getLogType() != null && s.getErrorDescription() != null).collect(Collectors.toList());
            ModelMapper modelMapper = new ModelMapper();
            List<LogTable> logTableList = modelMapper.map(employeeList, new TypeToken<List<LogTable>>() {
            }.getType());
            logTableRepository.saveAll(logTableList);


            /*
             * save to emplyoee table
             * */
            employees.removeAll(employeeList);
            employeeRepository.saveAll(employees);

        } catch (FileNotFoundException e) {
            log.info(e.getLocalizedMessage());
        } catch (IOException e) {
            log.info(e.getLocalizedMessage());
        }

    }

    @Override
    public List<Employee> getAllEmolyoeeData() {
        return employeeRepository.findAll();
    }

}
