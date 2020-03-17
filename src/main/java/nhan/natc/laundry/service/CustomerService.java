package nhan.natc.laundry.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nhan.natc.laundry.data.local.CustomerDAO;
import nhan.natc.laundry.data.remote.CustomerDto;
import nhan.natc.laundry.data.repository.CustomerRepository;
import nhan.natc.laundry.exception.DefaultException;

@Service("customerService")
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	public CustomerDAO createCustomer(CustomerDto customerDto) {
		CustomerDAO customerDao = new CustomerDAO(customerDto.getName(), customerDto.getEmail(), customerDto.getAddress(), customerDto.getPhone());
		return customerRepository.save(customerDao);
	}
	
	public CustomerDAO updateCustomer(Long customerId, CustomerDto customerDto) {
		Optional<CustomerDAO> customer = customerRepository.findById(customerId);
		if (customer.isPresent()) {
			CustomerDAO customerDao = customer.get();
			customerDao.setAddress(customerDto.getAddress());
			customerDao.setEmail(customerDto.getEmail());
			customerDao.setName(customerDto.getName());
			customerDao.setPhone(customerDto.getPhone());
			return customerRepository.save(customerDao);
		}
		throw new DefaultException("Customer not found.");
	}
	
	public List<CustomerDAO> findAll() {
		return customerRepository.findAll();
	}
	
	public CustomerDAO findById(long id) {
		Optional<CustomerDAO> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			return customer.get();
		}
		throw new DefaultException("Customer not found.");
	}
}
