package pl.domluke.zlotytracking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.domluke.zlotytracking.domain.NoteTypeDto;
import pl.domluke.zlotytracking.service.NoteTypeService;

import java.util.List;

@RestController
@RequestMapping("/json")
public class JsonController {
    private final NoteTypeService noteTypeService;

    @Autowired
    public JsonController(NoteTypeService noteTypeService) {
        this.noteTypeService = noteTypeService;
    }

    @GetMapping("/edition/{denomination}")
    public List<NoteTypeDto> getEditionsOfDenomination (@PathVariable int denomination) {
        return noteTypeService.getEditionsOfDenomination(denomination);
    }

}
