package fr.perso.skillcheck.question.dto;

import java.util.List;

import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.tag.dto.TagDto;
import fr.perso.skillcheck.utils.UtilEntity;


public class QuestionDtoWithTags extends QuestionDto {

    private List<TagDto> tagList;

    public QuestionDtoWithTags() {}

    public QuestionDtoWithTags(Long id,String content, Boolean isMultipleAnswer, Integer timeLimit) {
        super(id, content, isMultipleAnswer, timeLimit);
    }

    public QuestionDtoWithTags(String content, Boolean isMultipleAnswer, Integer timeLimit) {
        super(content, isMultipleAnswer, timeLimit);
    }

    public QuestionDtoWithTags(Question question) {
        super(question);
    }

    /** TAG LIST **/

    public List<TagDto> getTagList() {
        return this.tagList;
    }

    public void setTagList(List<TagDto> tagList) {
        this.tagList = tagList;
    }

    public boolean hasTagList() {
        return !UtilEntity.isEmpty(this.tagList);
    }

}
