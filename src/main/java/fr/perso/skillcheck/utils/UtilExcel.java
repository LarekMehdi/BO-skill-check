package fr.perso.skillcheck.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import fr.perso.skillcheck.test.dto.TestExportDto;

public abstract class UtilExcel {
    
    public static byte[] exportTestList(List<TestExportDto> dtos) {

        Workbook workbook = null;
        ByteArrayOutputStream out = null;
        
        

        try {
            workbook = new XSSFWorkbook();
            out = new ByteArrayOutputStream();
            Sheet sheet = workbook.createSheet("Tests");
            
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Id");
            headerRow.createCell(0).setCellValue("Title");
            headerRow.createCell(1).setCellValue("Description");
            headerRow.createCell(2).setCellValue("CreatedBy");

            headerRow.createCell(4).setCellValue("QuestionId");
            headerRow.createCell(4).setCellValue("QuestionContent");
            headerRow.createCell(4).setCellValue("IsMultipleAnswer");
            headerRow.createCell(4).setCellValue("TimeLimit");
            headerRow.createCell(4).setCellValue("Difficulty");
            headerRow.createCell(4).setCellValue("CreatedBy");
            headerRow.createCell(4).setCellValue("Code");
            headerRow.createCell(4).setCellValue("Answer 1");
            headerRow.createCell(4).setCellValue("IsCorrect 1");
            headerRow.createCell(4).setCellValue("Answer 2");
            headerRow.createCell(4).setCellValue("IsCorrect 2");
            headerRow.createCell(4).setCellValue("Answer 3");
            headerRow.createCell(4).setCellValue("IsCorrect 3");
            headerRow.createCell(4).setCellValue("Answer 4");
            headerRow.createCell(4).setCellValue("IsCorrect 4");


            int rowId = 1;
            
            workbook.write(out);
            return out.toByteArray();

        } catch(IOException e) {
            throw new RuntimeException("An error occured while generating excel file for test list");
        } finally {
            try {
                if (workbook != null) workbook.close();
                if (out != null) out.close();
            } catch(IOException e) {
                System.err.println("An error occured while closing ressources: " + e.getMessage());
            }
            
        }
    }
}
