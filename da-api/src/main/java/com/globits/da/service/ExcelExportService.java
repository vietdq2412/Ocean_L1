package com.globits.da.service;

import com.globits.da.dto.EmployeeDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportService {
    private XSSFWorkbook workbook;
    private Sheet sheet;

    public ExcelExportService() {
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(XSSFWorkbook workbook) {
        this.workbook = workbook;
        setSheet(workbook.createSheet());
    }

    private void writeHeaderRows() {
        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

//         Create header row
        Row headerRow = sheet.createRow(0);
        String[] columns = {"STT", "Tên", "Mã", "Email", "Phone", "Age"};
        for (int col = 0; col < columns.length; col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(columns[col]);
            cell.setCellStyle(headerCellStyle);
        }
    }

    private void writeDataRows(List<EmployeeDto> employees) {
        // Create other rows and cells with employees data
        int rowIdx = 1;
        for (EmployeeDto employee : employees) {
            Row row = sheet.createRow(rowIdx++);

            row.createCell(0).setCellValue(rowIdx - 1);
            row.createCell(1).setCellValue(employee.getName());
            row.createCell(2).setCellValue(employee.getCode());
            row.createCell(3).setCellValue(employee.getEmail());
            row.createCell(4).setCellValue(employee.getPhone());
            row.createCell(5).setCellValue(employee.getAge());
        }
    }

    public void exportDataToExcel(List<EmployeeDto> employees, HttpServletResponse response) throws IOException {
        writeHeaderRows();
        writeDataRows(employees);

        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        workbook.close();
    }

//    public ByteArrayInputStream exportDataToExcel(List<EmployeeDto> employees) throws IOException {
//        String[] columns = {"STT", "Tên", "Mã", "Email", "Phone", "Age"};
//
//        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
//            Sheet sheet = workbook.createSheet("Employees");
//
//            // Create a Font for styling header cells
//            Font headerFont = workbook.createFont();
//            headerFont.setBold(true);
//            headerFont.setColor(IndexedColors.BLACK.getIndex());
//
//            // Create a CellStyle with the font
//            CellStyle headerCellStyle = workbook.createCellStyle();
//            headerCellStyle.setFont(headerFont);
//
//            // Create header row
//            Row headerRow = sheet.createRow(0);
//
//            for (int col = 0; col < columns.length; col++) {
//                Cell cell = headerRow.createCell(col);
//                cell.setCellValue(columns[col]);
//                cell.setCellStyle(headerCellStyle);
//            }
//
//            // Create other rows and cells with employees data
//            int rowIdx = 1;
//            for (EmployeeDto employee : employees) {
//                Row row = sheet.createRow(rowIdx++);
//
//                row.createCell(0).setCellValue(rowIdx - 1);
//                row.createCell(1).setCellValue(employee.getName());
//                row.createCell(2).setCellValue(employee.getCode());
//                row.createCell(3).setCellValue(employee.getEmail());
//                row.createCell(4).setCellValue(employee.getPhone());
//                row.createCell(5).setCellValue(employee.getAge());
//            }
//
//            workbook.write(out);
//            return new ByteArrayInputStream(out.toByteArray());
//        }
//    }
}
