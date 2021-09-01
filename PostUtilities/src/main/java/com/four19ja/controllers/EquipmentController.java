package com.four19ja.controllers;

import com.four19ja.entities.Equipment;
import com.four19ja.services.EquipmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/postUtilities")
public class EquipmentController {
    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping(path = "/addEquipment")
    public @ResponseBody
    String addNewEquipment(@RequestParam String name) {
        return equipmentService.addNewEquipment(name);
    }

    @GetMapping(path = "/allEquipment")
    public @ResponseBody
    Iterable<Equipment> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }
}
