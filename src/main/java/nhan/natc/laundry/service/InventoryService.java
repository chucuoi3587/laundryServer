package nhan.natc.laundry.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import nhan.natc.laundry.data.local.InventoryDAO;
import nhan.natc.laundry.data.remote.InventoryDto;
import nhan.natc.laundry.data.remote.InventoryFilterRequest;
import nhan.natc.laundry.data.repository.InventoryRepository;
import nhan.natc.laundry.exception.DefaultException;

@Service("inventoryService")
public class InventoryService {

	@Autowired
	InventoryRepository inventoryRepository;
	
	public InventoryDAO createInventory(InventoryDto inventoryDto) {
		InventoryDAO inventoryDao = new InventoryDAO(inventoryDto.getName());
		return inventoryRepository.save(inventoryDao);
	}
	
	public InventoryDAO updateInventory(InventoryDto inventoryDto) {
		Optional<InventoryDAO> inventory = inventoryRepository.findById(inventoryDto.getId());
		if (inventory.isPresent()) {
			InventoryDAO inventoryDao = inventory.get();
			inventoryDao.setName(inventoryDto.getName());
			return inventoryRepository.save(inventoryDao);
		}
		throw new DefaultException("Inventory not found.");
	}
	
	public List<InventoryDAO> getAll(InventoryFilterRequest filter) {
		Pageable page = PageRequest.of(filter.getFetchPage(), filter.getFetchLimit());
		Page<InventoryDAO> inventories = inventoryRepository.findAll(page);
		filter.setHasMoreRecord(filter.getFetchPage() < inventories.getTotalPages() - 1);
		return inventories.stream()
				.collect(Collectors.toList());
	}
}
