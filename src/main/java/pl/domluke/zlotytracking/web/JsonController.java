package pl.domluke.zlotytracking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.domluke.zlotytracking.domain.LocationZipCode;
import pl.domluke.zlotytracking.domain.NoteTypeDto;
import pl.domluke.zlotytracking.service.LocationZipCodeService;
import pl.domluke.zlotytracking.service.NoteTypeService;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/json")
public class JsonController {
    private final NoteTypeService noteTypeService;
    private final LocationZipCodeService locationService;

    @Autowired
    public JsonController(NoteTypeService noteTypeService, LocationZipCodeService locationService) {
        this.noteTypeService = noteTypeService;
        this.locationService = locationService;
    }

    @GetMapping("/edition/{denomination}")
    public List<NoteTypeDto> getEditionsOfDenomination(@PathVariable int denomination) {
        return noteTypeService.getEditionsOfDenomination(denomination);
    }

    @GetMapping("/counties/{voivodeship}")
    public List<String> getCountiesOfVoivodeship(@PathVariable String voivodeship) {
        return locationService.getCounties(voivodeship);
    }

    @GetMapping("/places/{voivodeship}/{county}")
    public List<LocationZipCode> getPlacesOfCounties(@PathVariable String voivodeship, @PathVariable String county) {
        return locationService.getPlaces(voivodeship, county);
    }
}
