package nhan.natc.laundry.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nhan.natc.laundry.data.local.CustomerDAO;

public interface CustomerRepository extends JpaRepository<CustomerDAO, Long>{

}
