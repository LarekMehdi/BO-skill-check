package fr.perso.skillcheck.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.perso.skillcheck.tag.dto.TagDto;

@Service
public class TagService {

    @Autowired
    private TagRepository   tagRepository;

    /** CREATE **/

    public Tag create(TagDto dto) {
        Tag tag = new Tag(dto);
        return this.tagRepository.save(tag);
    }
}
