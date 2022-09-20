package pl.domluke.zlotytracking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.domluke.zlotytracking.domain.LocationZipCode;
import pl.domluke.zlotytracking.service.LocationZipCodeService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/admin/zipCodes")
public class ZipCodeController {
    private final LocationZipCodeService locationZipCodeService;

    @Autowired
    public ZipCodeController(LocationZipCodeService locationZipCodeService) {
        this.locationZipCodeService = locationZipCodeService;
    }

    @GetMapping("")
    public String home() {
        return "forward:/admin/zipCodes/page/1";
    }

    @GetMapping("/page/{page}")
    public String homePaged(Model model, @PathVariable Integer page) {
        Page<LocationZipCode> zipCodesPage = locationZipCodeService.getAllOnPages((page - 1), 20);
        model.addAttribute("zipCodes", zipCodesPage.getContent());
        model.addAttribute("page", zipCodesPage.getNumber() + 1);
        model.addAttribute("pages", zipCodesPage.getTotalPages());
        return "zipCode/home";
    }

    @GetMapping("/edit/{id}")
    public String editForm(Model model, @PathVariable int id) {
        model.addAttribute("locationZipCode", locationZipCodeService.getLocationById(id)
                .orElse(new LocationZipCode()));
        return "zipCode/form";
    }

    @PostMapping("/edit/{id}")
    public String editZipCode(@Valid LocationZipCode locationZipCode, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "zipCode/form";
        }
        locationZipCodeService.save(locationZipCode);
        model.addAttribute("message", "Zapisano lokalizację " + locationZipCode.getPlace());
        return "zipCode/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, HttpServletRequest request) {
        locationZipCodeService.delete(id);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
