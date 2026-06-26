package com.unauth.service;

import java.util.List;

import com.unauth.dto.request.RegistrationDto;
import com.unauth.entity.UnAuthUser;
import com.unauth.exception.AdminException;
import com.unauth.exception.UnAuthUserException;

public interface UnAuthUserService {
	
	public UnAuthUser addUnAuthUser(RegistrationDto user) throws UnAuthUserException;
	
	public UnAuthUser getUnAuthUserById(Long id) throws UnAuthUserException;
	
	public UnAuthUser updateUserById(Long id, RegistrationDto dto) throws UnAuthUserException;
	
	public UnAuthUser getUnAuthUserByEmail(String mail) throws UnAuthUserException;
	
	public UnAuthUser getUnAuthUserByUsername(String mail) throws UnAuthUserException;
	
	public List<String> getAllUnauthUsername() ;
	
	public List<UnAuthUser> getUnauAuthUserByStatus(String status) throws UnAuthUserException, AdminException;
	
	public UnAuthUser deleteUnAuthUserById(Long id) throws UnAuthUserException;
	
	public String rejectUserById(Long id) throws UnAuthUserException;
	
	
	
}
