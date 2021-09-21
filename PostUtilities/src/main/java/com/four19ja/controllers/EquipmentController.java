package com.four19ja.controllers;

import com.four19ja.entities.Equipment;
import com.four19ja.services.EquipmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/postUtilities/equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping(path = "/add")
    String addNewEquipment(@RequestParam String name) {
        return equipmentService.addNewEquipment(name);
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Equipment> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    @PostMapping(path = "/update")
    public @ResponseBody String updateEquipment(@RequestParam Integer id, @RequestParam String name) {
        return equipmentService.updateEquipment(id, name);
    }

    @PostMapping(path = "/delete")
    public @ResponseBody String deleteEquipment(@RequestParam Integer id) {
        return equipmentService.deleteEquipment(id);
    }
}
