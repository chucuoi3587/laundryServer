package nhan.natc.laundry.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import nhan.natc.laundry.data.local.CustomerDAO;
import nhan.natc.laundry.data.remote.CustomerDto;
import nhan.natc.laundry.data.remote.CustomerFilterRequest;
import nhan.natc.laundry.data.repository.CustomerRepository;
import nhan.natc.laundry.exception.DefaultException;
import nhan.natc.laundry.payload.CustomerResponse;

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
	
	public List<CustomerResponse> findAll(CustomerFilterRequest filter) {
		Pageable page = PageRequest.of(filter.getFetchPage(), filter.getFetchLimit());
		Page<CustomerDAO> customerList = customerRepository.findAll(page);
		filter.setHasMoreRecord(filter.getFetchPage() < customerList.getTotalPages() - 1);
		return customerList.stream()
				.map(CustomerResponse::fromEntity)
				.collect(Collectors.toList());
	}
	
	public CustomerDAO findById(long id) {
		Optional<CustomerDAO> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			return customer.get();
		}
		throw new DefaultException("Customer not found.");
	}
}
