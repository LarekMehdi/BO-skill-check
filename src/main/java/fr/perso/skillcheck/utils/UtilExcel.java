package fr.perso.skillcheck.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import fr.perso.skillcheck.answer.dto.SmallAnswerDto;
import fr.perso.skillcheck.constants.Difficulty;
import fr.perso.skillcheck.question.dto.QuestionExportDto;
import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.test.Test;
import fr.perso.skillcheck.test.dto.TestExportDto;

public abstract class UtilExcel {

    /** EXPORT **/
    
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

    /** IMPORT **/

    public static List<Test> importExcel(InputStream inputStream, UserPrincipal user) {
        List<Test> testList = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                Long testId = (long) row.getCell(0).getNumericCellValue();
                String testTitle = row.getCell(1).getStringCellValue();
                String testDescription = row.getCell(2).getStringCellValue();
                Long testCreatedBy = user.getId();

                Long questionId = (long) row.getCell(4).getNumericCellValue();
                String questionContent = row.getCell(5).getStringCellValue();
                String questionCode = row.getCell(6).getStringCellValue();
                Boolean questionIsMultipleAnswer = row.getCell(7).getBooleanCellValue();
                Double questionTimeLimit = row.getCell(8).getNumericCellValue();
                Difficulty questionDifficulty = Difficulty.valueOf(row.getCell(9).getStringCellValue());
                Long questionCreatedBy = user.getId();

                String answer1Content = row.getCell(11).getStringCellValue();
                Boolean answer1IsCorrect = row.getCell(12).getBooleanCellValue();
            }

        } catch (IOException e) {
            throw new RuntimeException("An error occured while reading file", e);
        }

        return testList;
    }

    /** PRIVATES **/

    private static String getString(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.STRING) {
            String v = cell.getStringCellValue();
            return v.isBlank() ? null : v;
        }
        if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        }
        return null;
    }

    private static Boolean getBoolean(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.BOOLEAN) {
            return cell.getBooleanCellValue();
        }
        if (cell.getCellType() == CellType.STRING) {
            String v = cell.getStringCellValue().trim().toLowerCase();
            if (v.equals("true") || v.equals("1") || v.equals("yes") || v.equals("y") || v.equals("vrai")) return true;
            if (v.equals("false") || v.equals("0") || v.equals("no") || v.equals("n") || v.equals("faux")) return false;
        }
        return null;
    }

    private static Long getLong(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC) {
            return (long) cell.getNumericCellValue();
        }
        if (cell.getCellType() == CellType.STRING) {
            String v = cell.getStringCellValue();
            if (v.isEmpty()) return null;
            try {
                return Long.parseLong(v);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    private static Double getDouble(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        }
        if (cell.getCellType() == CellType.STRING) {
            String v = cell.getStringCellValue();
            if (v.isEmpty()) return null;
            try {
                return Double.parseDouble(v);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    private static Integer getInteger(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        }
        if (cell.getCellType() == CellType.STRING) {
            String v = cell.getStringCellValue();
            if (v.isEmpty()) return null;
            try {
                return Integer.parseInt(v);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }
}
