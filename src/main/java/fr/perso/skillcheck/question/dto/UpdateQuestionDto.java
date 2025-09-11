package fr.perso.skillcheck.question.dto;

import java.util.List;

import fr.perso.skillcheck.answer.dto.SmallAnswerDto;
import fr.perso.skillcheck.constants.Difficulty;
import fr.perso.skillcheck.utils.UtilEntity;

public class UpdateQuestionDto {
    
    private Long                        id;
    private String                      content;
    private String                      code;
    private Integer                     timeLimit;
    private Difficulty                  difficulty;
    private List<SmallAnswerDto>        answerList;

    public UpdateQuestionDto() {}

    public UpdateQuestionDto(Long id, String content, String code, Integer timeLimit, Difficulty difficulty, List<SmallAnswerDto> answers) {
        this.id = id;
        this.content = content;
        this.code = code;
        this.timeLimit = timeLimit;
        this.difficulty = difficulty;
        this.answerList = answers;
    }

    /** ID **/

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean hasId() {
        return !UtilEntity.isEmpty(this.id);
    }

    /** CONTENT **/

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean hasContent() {
        return !UtilEntity.isEmpty(this.content);
    }

    /** CODE **/

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean hasCode() {
        return !UtilEntity.isEmpty(this.code);
    }

    /** TIME LIMIT **/

    public Integer getTimeLimit() {
        return this.timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public boolean hasTimeLimit() {
        return !UtilEntity.isEmpty(this.timeLimit);
    }

    /** DIFFICULTY **/

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public boolean hasDifficulty() {
        return !UtilEntity.isEmpty(this.difficulty);
    }

    /** ANSWERS **/

    public List<SmallAnswerDto> getAnswerList() {
        return this.answerList;
    }

    public void setAnswerList(List<SmallAnswerDto> answerList) {
        this.answerList = answerList;
    }

    public boolean hasAnswerList() {
        return !UtilEntity.isEmpty(this.answerList);
    }
}
