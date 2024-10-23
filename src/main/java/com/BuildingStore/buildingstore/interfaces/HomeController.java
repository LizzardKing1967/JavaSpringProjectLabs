package com.BuildingStore.buildingstore.interfaces;
import com.BuildingStore.buildingstore.materialRepository.MaterialRepository;

import com.BuildingStore.buildingstore.model.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private MaterialRepository materialRepository;
    @ModelAttribute("productList")
    public List<Material> addMaterialsIntoModel() {
        List<Material> materials = new ArrayList<>();
        materialRepository.findAll().forEach(materials::add);
        return materials;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("storeTitle", "Building store");
        return "index";
    }
}
