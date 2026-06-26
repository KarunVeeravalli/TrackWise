package com.unauth.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unauth.entity.UnAuthUser;

public interface UnAuthUserRepo extends JpaRepository<UnAuthUser, Long>{
	
	UnAuthUser findByEmail(String mail);
	
	UnAuthUser findByUsername(String name);
	
	@Query("SELECT u.username FROM UnAuthUser u")
	List<String> findAllUsername();

	List<UnAuthUser> findAllByStatus(String status);
	
	

}
