package pl.domluke.zlotytracking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.domluke.zlotytracking.domain.LocationZipCode;
import pl.domluke.zlotytracking.service.LocationZipCodeService;

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
}
