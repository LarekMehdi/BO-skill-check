package fr.perso.skillcheck.question.dto;

import java.util.List;
import fr.perso.skillcheck.utils.UtilEntity;


public class QuestionDtoWithTagIds extends QuestionDto {

    private List<Long> tagIds;

    public QuestionDtoWithTagIds() {}

    public QuestionDtoWithTagIds(Long id,String content, String code, Boolean isMultipleAnswer, Integer timeLimit) {
        super(id, content, code, isMultipleAnswer, timeLimit);
    }

    public QuestionDtoWithTagIds(String content, Boolean isMultipleAnswer, Integer timeLimit) {
        super(content, isMultipleAnswer, timeLimit);
    }

    /** TAG IDS **/

    public List<Long> getTagIds() {
        return this.tagIds;
    }

    public void setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
    }

    public boolean hasTagIds() {
        return !UtilEntity.isEmpty(this.tagIds);
    }

}
