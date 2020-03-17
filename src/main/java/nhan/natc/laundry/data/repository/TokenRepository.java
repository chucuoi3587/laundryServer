package nhan.natc.laundry.data.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import nhan.natc.laundry.data.local.TokenDAO;

@Repository
public interface TokenRepository extends JpaRepository<TokenDAO, Long>{

//	@Query(value = "Delete tokendao Where userid = ?1", nativeQuery = true)
//	public void deleteTokenByUserId(Long userId);
	@Query(value = "Select expire_time FROM token WHERE accesstoken = ?1", nativeQuery = true)
	Optional<Long> findExpiredTimeByToken(String token);
	
	@Query(value = "Select * FROM token WHERE accesstoken = ?1", nativeQuery = true)
	Optional<TokenDAO> findUserByToken(String token);
}
