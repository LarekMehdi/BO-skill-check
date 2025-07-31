package fr.perso.skillcheck.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.perso.skillcheck.tag.dto.TagDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired 
    private TagService  tagService;

    /** CREATE **/

    @PostMapping()
    public Tag create(@RequestBody @Valid TagDto dto) {
        return this.tagService.create(dto);
    }
    
}
