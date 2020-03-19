package nhan.natc.laundry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nhan.natc.laundry.data.remote.InventoryDto;
import nhan.natc.laundry.data.remote.InventoryFilterRequest;
import nhan.natc.laundry.payload.DefaultResponse;
import nhan.natc.laundry.service.InventoryService;

@RestController
public class InventoryController {

	@Autowired
	InventoryService inventoryService;
	
	@PostMapping("/inventory/add")
	public ResponseEntity<DefaultResponse> createInventory(@RequestBody InventoryDto inventoryDto) {
		return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(inventoryService.createInventory(inventoryDto)));	
	}
	
	@PostMapping("/inventory/update")
	public ResponseEntity<DefaultResponse> updateInventory(@RequestBody InventoryDto inventoryDto) {
		return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(inventoryService.updateInventory(inventoryDto)));
	}
	
	@PostMapping("/inventory/all")
	public ResponseEntity<DefaultResponse> getAll(@RequestBody InventoryFilterRequest filter) {
		return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(inventoryService.getAll(filter)));
	}
}
