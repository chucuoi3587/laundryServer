package nhan.natc.laundry.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nhan.natc.laundry.data.local.RoleDAO;

@Repository
public interface RoleRepository extends JpaRepository<RoleDAO, Integer>{

}
