package com.codeup.coffee2.controllers;

import com.codeup.coffee2.model.Ad;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdController {

    @GetMapping("/ads")
    public String showAds(Model model) {
        List<Ad> adList = new ArrayList<>();
        Ad ad = new Ad("1967 Buick Widlcat", "Cherry car");
        adList.add(ad);
        ad = new Ad("Wooden Desk", "Rolltop");
        adList.add(ad);
        model.addAttribute("ads", adList);
        return "ads/index";
    }

    @GetMapping("/ad")
    public String showAd(Model model) {
        Ad ad = new Ad("Mac Book pro", "A 1905 Mac Book pro developed by Charles Babbage.");
        model.addAttribute("ad", ad);
        return "ads/show";
    }

    @GetMapping("/ads/create")
    public String gotoCreateAdForm(Model model) {
        return "ads/create";
    }

    @PostMapping("/ads/create")
    public String createAd(@RequestParam(name = "title") String title,
                           @RequestParam(name = "description") String description,
                           Model model) {
        Ad ad = new Ad(title, description);
        model.addAttribute("ad", ad);
        return "ads/show";
    }
}
