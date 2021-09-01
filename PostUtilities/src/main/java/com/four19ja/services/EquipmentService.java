package com.four19ja.services;

import com.four19ja.entities.Equipment;
import com.four19ja.repositories.EquipmentRepository;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public String addNewEquipment(String name) {
        Equipment e = new Equipment();
        e.setName(name);
        equipmentRepository.save(e);
        return "Saved";
    }

    public Iterable<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }
}
