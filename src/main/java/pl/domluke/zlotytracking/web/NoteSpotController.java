package pl.domluke.zlotytracking.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.domluke.zlotytracking.domain.LocationZipCode;
import pl.domluke.zlotytracking.domain.LoggedUser;
import pl.domluke.zlotytracking.domain.NoteSpotDto;
import pl.domluke.zlotytracking.service.LocationZipCodeService;
import pl.domluke.zlotytracking.service.NoteSpotService;
import pl.domluke.zlotytracking.service.NoteTypeService;
import pl.domluke.zlotytracking.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("user/notes")
public class NoteSpotController {
    private final NoteTypeService noteTypeService;
    private final LocationZipCodeService locationService;
    private final NoteSpotService noteSpotService;

    public NoteSpotController(NoteTypeService noteTypeService, LocationZipCodeService locationService,
                              NoteSpotService noteSpotService, UserService userService) {
        this.noteTypeService = noteTypeService;
        this.locationService = locationService;
        this.noteSpotService = noteSpotService;
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
                               @AuthenticationPrincipal LoggedUser loggedUser) {
        noteSpotDto.setNoteSerialNumber(noteSpotDto.getNoteSerialNumber().toUpperCase());
        if (bindingResult.hasErrors()) {
            loadDataForFormToModel(model);
            return "user/noteForm";
        }
        noteSpotService.save(noteSpotDto, loggedUser.getUser());
        //sprawdzenie czy nie było hitów -> komunikat
        loadDataForFormToModel(model);
        model.addAttribute("message", "zapisano");
        return "user/noteForm";
    }

    @GetMapping({"/spots", ""})
    public String showSpots() {
        return "forward:/user/notes/spots/1";
    }

    @GetMapping("/spots/{page}")
    public String showSpotsPaged(Model model, @PathVariable Integer page,
                                 @AuthenticationPrincipal LoggedUser loggedUser) {
        model.addAttribute("page",
                noteSpotService.getAllByUserOnPages(loggedUser.getUser(), (page -1), 20));
        return "user/spotsList";
    }

    @GetMapping("/show/{id}")
    public String showNote(@PathVariable long id, Model model) {
        model.addAttribute("note", noteSpotService.getNoteBySpotId(id));
        return "note/show";
    }
}
