package com.mobilehub;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CatalogController {
    private final PhoneService phoneService;

    public CatalogController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping("/")
    public String home(@RequestParam(required = false) String q,
                       @RequestParam(required = false) String brand,
                       @RequestParam(required = false) Integer maxPrice,
                       Model model) {
        model.addAttribute("phones", phoneService.search(q, brand, maxPrice));
        model.addAttribute("q", q == null ? "" : q);
        model.addAttribute("brand", brand == null ? "All brands" : brand);
        model.addAttribute("maxPrice", maxPrice == null ? "" : maxPrice);
        return "index";
    }
}
