package nhan.natc.laundry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nhan.natc.laundry.data.remote.CustomerDto;
import nhan.natc.laundry.payload.DefaultResponse;
import nhan.natc.laundry.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@PostMapping("/customer/add")
	public ResponseEntity<DefaultResponse> createCustomer(@RequestBody CustomerDto customerDto) {
		return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(customerService.createCustomer(customerDto)));	
	}
	
	@PostMapping("/customer/update")
	public ResponseEntity<DefaultResponse> updateCustomer(@RequestBody CustomerDto customerDto) {
		return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(customerService.updateCustomer(customerDto.getId(), customerDto)));
	}
	
	@GetMapping("/customer/all")
	public ResponseEntity<DefaultResponse> getCustomerList() {
		return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(customerService.findAll()));		
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<DefaultResponse> getCustomer(@PathVariable("customerId") long customerId) {
		return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(customerService.findById(customerId)));		
	}
}
