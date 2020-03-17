package nhan.natc.laundry.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nhan.natc.laundry.data.local.InventoryDAO;

public interface InventoryRepository extends JpaRepository<InventoryDAO, String>{

}
