package pl.domluke.zlotytracking.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.domluke.zlotytracking.domain.LocationZipCode;
import pl.domluke.zlotytracking.domain.NoteSpotDto;
import pl.domluke.zlotytracking.service.LocationZipCodeService;
import pl.domluke.zlotytracking.service.NoteSpotService;
import pl.domluke.zlotytracking.service.NoteTypeService;
import pl.domluke.zlotytracking.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("user/notes")
public class NoteSpotController {
    private final NoteTypeService noteTypeService;
    private final LocationZipCodeService locationService;
    private final NoteSpotService noteSpotService;

    private final UserService userService;

    public NoteSpotController(NoteTypeService noteTypeService, LocationZipCodeService locationService,
                              NoteSpotService noteSpotService, UserService userService) {
        this.noteTypeService = noteTypeService;
        this.locationService = locationService;
        this.noteSpotService = noteSpotService;
        this.userService = userService;
    }

    public void loadDataForFormToModel(Model model) {
        model.addAttribute("noteTypes", noteTypeService.getDenominations());
        model.addAttribute("voivodeships", locationService.getVoivodeships());
        NoteSpotDto noteSpotDto = (NoteSpotDto) model.getAttribute("noteSpotDto");
        if (noteSpotDto != null) {
            LocationZipCode place = noteSpotDto.getPlace();
            if (place != null ) {
                model.addAttribute("counties", locationService.getCounties(place.getVoivodeship()));
                model.addAttribute("places",
                        locationService.getPlaces(place.getVoivodeship(), place.getCounty()));
            }
        }
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable long id, Model model) {
        model.addAttribute("noteSpotDto", noteSpotService.getNoteSpotById(id));
        loadDataForFormToModel(model);
        return "user/noteForm";
    }

    @PostMapping("/edit/{id}")
    public String saveNoteSpot(@Valid NoteSpotDto noteSpotDto, BindingResult bindingResult, Model model,
                               Principal principal) {
        noteSpotDto.setNoteSerialNumber(noteSpotDto.getNoteSerialNumber().toUpperCase());
        if (bindingResult.hasErrors()) {
            loadDataForFormToModel(model);
            return "user/noteForm";
        }
        noteSpotService.save(noteSpotDto, userService.findUserByName(principal.getName()));
        //sprawdzenie czy nie było hitów -> komunikat
        loadDataForFormToModel(model);
        model.addAttribute("message", "zapisano");
        return "user/noteForm";
    }
}
