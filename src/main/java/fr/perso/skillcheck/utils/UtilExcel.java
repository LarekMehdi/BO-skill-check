package fr.perso.skillcheck.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import fr.perso.skillcheck.answer.dto.SmallAnswerDto;
import fr.perso.skillcheck.constants.Difficulty;
import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.question.dto.QuestionExportDto;
import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.tag.dto.TagDto;
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
            headerRow.createCell(11).setCellValue("Tags");
            
            headerRow.createCell(12).setCellValue("Answer 1");
            headerRow.createCell(13).setCellValue("IsCorrect 1");
            headerRow.createCell(14).setCellValue("Answer 2");
            headerRow.createCell(15).setCellValue("IsCorrect 2");
            headerRow.createCell(16).setCellValue("Answer 3");
            headerRow.createCell(17).setCellValue("IsCorrect 3");
            headerRow.createCell(18).setCellValue("Answer 4");
            headerRow.createCell(19).setCellValue("IsCorrect 4");

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
                    row.createCell(11).setCellValue(question.getTags().stream().map(TagDto::getLabel).collect(Collectors.joining(";")));

                    for(int i=0; i<answerMax; i++) {
                        if (i < question.getAnswers().size()) {
                            SmallAnswerDto a = question.getAnswers().get(i);
                            row.createCell(12 + i*2).setCellValue(a.getContent());
                            row.createCell(13 + i*2).setCellValue(a.getIsCorrect());
                        } else {
                            row.createCell(12 + i*2).setCellValue("");
                            row.createCell(13 + i*2).setCellValue("");
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

    public static List<TestExportDto> importExcel(InputStream inputStream, UserPrincipal user) {
        List<TestExportDto> dtos = new ArrayList<>();

        List<Test> testList = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            Set<Long> testIds = new HashSet<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                Long testId = getLong(row.getCell(0));
                String testTitle = getString(row.getCell(1));
                String testDescription = getString(row.getCell(2));
                Long testCreatedBy = user.getId();

                Long questionId = getLong(row.getCell(4));
                String questionContent = getString(row.getCell(5));
                String questionCode = getString(row.getCell(6));
                Boolean questionIsMultipleAnswer = getBoolean(row.getCell(7));
                Integer questionTimeLimit = getInteger(row.getCell(8));
                Difficulty questionDifficulty = Difficulty.valueOf(row.getCell(9).getStringCellValue());
                Long questionCreatedBy = user.getId();

                String tagLabelList = getString(row.getCell(11));
                String answer1Content = getString(row.getCell(12));
                Boolean answer1IsCorrect = getBoolean(row.getCell(13));
                String answer2Content = getString(row.getCell(14));
                Boolean answer2IsCorrect = getBoolean(row.getCell(15));
                String answer3Content = getString(row.getCell(16));
                Boolean answer3IsCorrect = getBoolean(row.getCell(17));
                String answer4Content = getString(row.getCell(18));
                Boolean answer4IsCorrect = getBoolean(row.getCell(19));

                Test test = testList.stream().filter(t -> Objects.equals(t.getId(), testId)).findFirst().orElse(null);
                TestExportDto dto = dtos.stream().filter(d ->Objects.equals(d.getId(), testId)).findFirst().orElse(null);

                if (test == null) {
                    test = new Test(testId, testTitle, testDescription, testCreatedBy);
                    testList.add(test);
                    testIds.add(testId);

                    dto = new TestExportDto(test);
                    dtos.add(dto);
                }

                Question question = new Question(questionId, questionContent, questionCode, questionIsMultipleAnswer, 0.0, questionTimeLimit, questionDifficulty, questionCreatedBy);
                QuestionExportDto qDto = new QuestionExportDto(question);

                List<TagDto> tagDtos = new ArrayList<>();
                List<String> labels = List.of(tagLabelList.split(";"));
                for (String label : labels) {
                    TagDto tDto = new TagDto(label);
                    tagDtos.add(tDto);
                }
                qDto.setTags(tagDtos);

                List<SmallAnswerDto> answerList = new ArrayList<>();
                if (answer1Content != null) {
                    SmallAnswerDto a = new SmallAnswerDto(answer1Content, answer1IsCorrect);
                    answerList.add(a);
                }
                if (answer2Content != null) {
                    SmallAnswerDto a = new SmallAnswerDto(answer2Content, answer2IsCorrect);
                    answerList.add(a);
                }
                if (answer3Content != null) {
                    SmallAnswerDto a = new SmallAnswerDto(answer3Content, answer3IsCorrect);
                    answerList.add(a);
                }
                if (answer4Content != null) {
                    SmallAnswerDto a = new SmallAnswerDto(answer4Content, answer4IsCorrect);
                    answerList.add(a);
                }
                qDto.setAnswers(answerList);

                List<QuestionExportDto> currentQuestions = new ArrayList<>();
                currentQuestions.addAll(dto.getQuestions());
                currentQuestions.add(qDto);
                dto.setQuestions(currentQuestions);

            }

        } catch (IOException e) {
            throw new RuntimeException("An error occured while reading file", e);
        }

        return dtos;
    }

    /** GETTERS **/

    public static String getString(Cell cell) {
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

    public static Boolean getBoolean(Cell cell) {
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

    public static Long getLong(Cell cell) {
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

    public static Double getDouble(Cell cell) {
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

    public static Integer getInteger(Cell cell) {
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
