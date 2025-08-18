package fr.perso.skillcheck.test.dto;

import java.util.List;

import fr.perso.skillcheck.question.dto.QuestionExportDto;

public class TestExportDto {
    private Long                        id;
    private String                      title;
    private String                      description;
    private Long                        createdBy;
    private List<QuestionExportDto>     questions;
}
