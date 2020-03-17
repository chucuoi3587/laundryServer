package nhan.natc.laundry.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nhan.natc.laundry.data.local.EquipmentDAO;
import nhan.natc.laundry.data.remote.EquipmentDto;
import nhan.natc.laundry.data.repository.EquipmentRepository;
import nhan.natc.laundry.exception.DefaultException;

@Service("equipmentService")
public class EquipmentService {
	
	@Autowired
	EquipmentRepository equipmentRepository;
	
	public EquipmentDAO createEquipment(EquipmentDto equipmentDto) {
		EquipmentDAO equipmentDao = new EquipmentDAO(equipmentDto.getName(), equipmentDto.getDescription());
		return equipmentRepository.save(equipmentDao);
	}
	
	public EquipmentDAO updateEquipment(EquipmentDto equipmentDto) {
		Optional<EquipmentDAO> equipment = equipmentRepository.findById(equipmentDto.getId());
		if (equipment.isPresent()) {
			EquipmentDAO equipmentDao = equipment.get();
			equipmentDao.setName(equipmentDto.getName());
			equipmentDao.setDescription(equipmentDto.getDescription());
			return equipmentRepository.save(equipmentDao);
		}
		throw new DefaultException("Equipment not found.");
	}

}
