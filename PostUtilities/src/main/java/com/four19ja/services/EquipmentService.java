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
        Equipment equipment = new Equipment();
        equipment.setName(name);
        equipmentRepository.save(equipment);
        return "Saved";
    }

    public Iterable<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    public String updateEquipment(Integer id, String name) {
        Equipment equipment = equipmentRepository.findById(id).orElse(null);
        if(equipment == null) {
            return "Not Saved";
        }
        equipment.setName(name);
        equipmentRepository.save(equipment);
        return "Saved";
    }

    public String deleteEquipment(Integer id) {
        Equipment equipment = equipmentRepository.findById(id).orElse(null);
        if(equipment == null) {
            return "Does not exist";
        }
        equipmentRepository.deleteById(id);
        return "Deleted";
    }
}
