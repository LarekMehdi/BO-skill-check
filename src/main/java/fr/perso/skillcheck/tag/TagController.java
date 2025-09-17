package fr.perso.skillcheck.tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.perso.skillcheck.tag.dto.TagDto;
import fr.perso.skillcheck.utils.QuerySearch;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired 
    private TagService  tagService;

    /** FIND ALL **/

    @GetMapping()
    public List<Tag> findAll() {
        return this.tagService.findAll();
    }

    @GetMapping("/search")
    public List<Tag> findAllByLabel(@ModelAttribute @Valid QuerySearch search) {
        return this.tagService.findAllByLabel(search);
    }

    /** FIND **/

    @GetMapping("/{id}")
    public Tag findById(@PathVariable("id") Long id) {
        return this.tagService.findById(id);
    }

    /** UPDATE **/

    @PutMapping("/{id}")
    public Tag update(@PathVariable("id") Long id, @RequestBody @Valid TagDto dto) {
        return this.tagService.update(id, dto);
    }

    /** CREATE **/

    @PostMapping()
    public Tag create(@RequestBody @Valid TagDto dto) {
        return this.tagService.create(dto);
    }

    /** DELETE **/

    @DeleteMapping("/{id}") 
    public void deleteById(@PathVariable("id") Long id) {
        this.tagService.deleteById(id);
    }
    
}
