package nhan.natc.laundry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nhan.natc.laundry.data.remote.EquipmentDto;
import nhan.natc.laundry.data.remote.EquipmentFilterRequest;
import nhan.natc.laundry.payload.DefaultResponse;
import nhan.natc.laundry.service.EquipmentService;

@RestController
public class EquipmentController {

	@Autowired
	EquipmentService equipmentService;
	
	@PostMapping("/equipment/add")
	public ResponseEntity<DefaultResponse> createEquipment(@RequestBody EquipmentDto equipmentDto) {
		return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(equipmentService.createEquipment(equipmentDto)));
	}
	
	@PostMapping("/equipment/update")
	public ResponseEntity<DefaultResponse> updateEquipment(@RequestBody EquipmentDto equipmentDto) {
		return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(equipmentService.updateEquipment(equipmentDto)));
	}
	
	@PostMapping("/equipment/all")
	public ResponseEntity<DefaultResponse> getAll(@RequestBody EquipmentFilterRequest filter) {
		return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(equipmentService.getAll(filter)));
	}
}
