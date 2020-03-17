package nhan.natc.laundry.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import nhan.natc.laundry.data.local.UserDAO;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, Long>{
	
	@Query(value = "Select * From User u Where u.email = ?1", nativeQuery = true)
	Optional<UserDAO> findUserPassword(String email);
	
}
