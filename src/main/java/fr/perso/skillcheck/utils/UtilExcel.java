package fr.perso.skillcheck.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import fr.perso.skillcheck.answer.dto.SmallAnswerDto;
import fr.perso.skillcheck.question.dto.QuestionExportDto;
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
            headerRow.createCell(1).setCellValue("Title");
            headerRow.createCell(2).setCellValue("Description");
            headerRow.createCell(3).setCellValue("CreatedBy");

            headerRow.createCell(4).setCellValue("QuestionId");
            headerRow.createCell(5).setCellValue("QuestionContent");
            headerRow.createCell(6).setCellValue("Code");
            headerRow.createCell(7).setCellValue("IsMultipleAnswer");
            headerRow.createCell(8).setCellValue("TimeLimit");
            headerRow.createCell(9).setCellValue("Difficulty");
            headerRow.createCell(10).setCellValue("QuestionCreatedBy");
            
            headerRow.createCell(11).setCellValue("Answer 1");
            headerRow.createCell(12).setCellValue("IsCorrect 1");
            headerRow.createCell(13).setCellValue("Answer 2");
            headerRow.createCell(14).setCellValue("IsCorrect 2");
            headerRow.createCell(15).setCellValue("Answer 3");
            headerRow.createCell(16).setCellValue("IsCorrect 3");
            headerRow.createCell(17).setCellValue("Answer 4");
            headerRow.createCell(18).setCellValue("IsCorrect 4");

            int answerMax = 4;
            int rowId = 1;
            for (TestExportDto dto : dtos) {
                for (QuestionExportDto question : dto.getQuestions()) {

                    Row row = sheet.createRow(rowId++);
                    row.createCell(0).setCellValue(dto.getId());
                    row.createCell(1).setCellValue(dto.getTitle());
                    row.createCell(2).setCellValue(dto.getDescription());
                    row.createCell(3).setCellValue(dto.getCreatedBy());
                    
                    row.createCell(4).setCellValue(question.getId());
                    row.createCell(5).setCellValue(question.getContent());
                    row.createCell(6).setCellValue(question.getCode());
                    row.createCell(7).setCellValue(question.getIsMultipleAnswer());
                    row.createCell(8).setCellValue(question.getTimeLimit());
                    row.createCell(9).setCellValue(question.getDifficulty().toString());
                    row.createCell(10).setCellValue(question.getCreatedBy());

                    for(int i=0; i<answerMax; i++) {
                        if (i < question.getAnswers().size()) {
                            SmallAnswerDto a = question.getAnswers().get(i);
                            row.createCell(11 + i*2).setCellValue(a.getContent());
                            row.createCell(12 + i*2).setCellValue(a.getIsCorrect());
                        } else {
                            row.createCell(11 + i*2).setCellValue("");
                            row.createCell(12 + i*2).setCellValue("");
                        }
                    }
                }
            }
            
            workbook.write(out);
            return out.toByteArray();

        } catch(IOException e) {
            throw new RuntimeException("An error occured while generating excel file for test list", e);
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
