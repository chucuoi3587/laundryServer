package nhan.natc.laundry.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nhan.natc.laundry.data.local.UserStatusDAO;

@Repository
public interface UserStastusRepository extends JpaRepository<UserStatusDAO, Integer>{

}
