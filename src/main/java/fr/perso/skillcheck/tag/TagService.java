package fr.perso.skillcheck.tag;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.perso.skillcheck.exceptions.NotFoundException;
import fr.perso.skillcheck.tag.dto.TagDto;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    /** FIND ALL **/

    public List<Tag> findAll() {
        List<Tag> tags = this.tagRepository.findAll();
        tags.sort(Comparator.comparing(Tag::getId));

        return tags;
    }

    public List<Tag> findAllByIds(List<Long> ids) {
        return this.tagRepository.findAllByIds(ids);
    }

    /** FIND **/

    public Tag findById(Long id) {
        return this.tagRepository.findById(id).orElse(null);
    }

    public Tag findByLabel(String label) {
        return this.tagRepository.findByLabel(label);
    }

    /** UPDATE **/

    public Tag update(Long id, TagDto dto) {
        Tag tag = this.tagRepository.findById(id).orElseThrow(() -> new NotFoundException("No tag found with id " + id));
        tag.setLabel(dto.getLabel());
    
        return this.tagRepository.save(tag);
    }

    /** CREATE **/

    public Tag create(TagDto dto) {
        Tag tag = new Tag(dto);
        return this.tagRepository.save(tag);
    }

    /** DELETE **/

    public void deleteById(Long id) {
        this.tagRepository.deleteById(id);
    }
}
