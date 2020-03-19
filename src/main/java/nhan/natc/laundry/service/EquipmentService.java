package nhan.natc.laundry.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import nhan.natc.laundry.data.local.EquipmentDAO;
import nhan.natc.laundry.data.remote.EquipmentDto;
import nhan.natc.laundry.data.remote.EquipmentFilterRequest;
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
	
	public List<EquipmentDAO> getAll(EquipmentFilterRequest filter) {
		Pageable page = PageRequest.of(filter.getFetchPage(), filter.getFetchLimit());
		Page<EquipmentDAO> equipments = equipmentRepository.findAll(page);
		filter.setHasMoreRecord(filter.getFetchPage() < equipments.getTotalPages() - 1);
		return equipments.stream()
				.collect(Collectors.toList());
	}

}
